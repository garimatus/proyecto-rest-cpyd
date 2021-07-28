package cl.utem.infb8090.z.ProyectoRest.rest_controllers.v1;


import java.io.Serializable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestHeader;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import java.util.ArrayList;

import cl.utem.infb8090.z.ProyectoRest.repository_manager.EarthquakeManager;
import cl.utem.infb8090.z.ProyectoRest.etc.ProyectoRestJwt;
import cl.utem.infb8090.z.ProyectoRest.etc.ProyectoRestScraper;
import cl.utem.infb8090.z.ProyectoRest.exceptions.ProyectoRestException;
import cl.utem.infb8090.z.ProyectoRest.value_objects.EarthquakeVO;
import cl.utem.infb8090.z.ProyectoRest.value_objects.ExceptionVO;
import cl.utem.infb8090.z.ProyectoRest.models.Earthquake;


@RestController
@RequestMapping(value = {"/v1"}, consumes = {"application/json;charset=utf-8"}, produces = {"application/json;charset=utf-8"})
public class EarthquakeRest implements Serializable {
    
    @Autowired
    private transient EarthquakeManager earthquakeManager;

    @Autowired
    private transient ProyectoRestScraper proyectoRestScraper;

    @Autowired
    private transient ProyectoRestJwt proyectoRestJwt;

    private static Logger LOGGER = LoggerFactory.getLogger(EarthquakeRest.class);

    @ApiOperation(value = "Obtener un arreglo de sismos como un array de objetos JSON mediante una autenticación")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Respuesta exitosa", response = EarthquakeVO.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "Acceso denegado", response = ExceptionVO.class),
        @ApiResponse(code = 403, message = "Sin permisos suficientes", response = ExceptionVO.class),
        @ApiResponse(code = 404, message = "Recurso no encontrado", response = ExceptionVO.class),
        @ApiResponse(code = 412, message = "Precondición fallida", response = ExceptionVO.class)
    })
    @GetMapping(value = {"/earthquakes"}, consumes = {"application/json;charset=utf-8"}, produces = {"application/json;charset=utf-8"})
    public ResponseEntity getAllEarthquakes(
    @ApiParam(name = "auth", value = "Bearer de autenticación de usuario", required = true, example = "bearer: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiIvdjEvYXV0aC9sb2dpbiIsImlkIjoiUHJveWVjdG9BcHBNb2JpbGUiLCJleHAiOjE2MjU5MzE0NTUsImlhdCI6MTYyNTkwNjI1NSwianRpIjoiYWJjMTIzIn0.qS4688Ae98Sr3m8p5C81ddtgNiW69fxpl-2u5YIVUbA, AndAnotherOne")
    @RequestHeader(name = "auth", required = true) String bearer) {

        String[] auth = StringUtils.split(bearer, ",");

        if (bearer == null || StringUtils.isBlank(bearer) || auth.length != 2) {
            throw new ProyectoRestException(400, "Petición inválida");
        }
        
        final String jwt = StringUtils.trimToEmpty(StringUtils.removeStartIgnoreCase(auth[0], "bearer: "));

        final String appToken = StringUtils.trimToEmpty(StringUtils.removeStartIgnoreCase(auth[1], " "));

        if (!proyectoRestJwt.validarJwt(jwt, appToken)) {
            final String message = "El/los token(s) utilizado(s) no es/son válido(s)";
            LOGGER.error(message);
            throw new ProyectoRestException(401, message);
        }
        
        final List<EarthquakeVO> scrapedEarthquakes = proyectoRestScraper.getLastEarthquakes();

        if (scrapedEarthquakes.isEmpty()) {
            final String message = "Ha ocurrido un problema conectándose con la página web http://www.sismologia.cl/links/lasts_sismos.html";
            LOGGER.error(message);
            throw new ProyectoRestException(401, message);
        }

        final EarthquakeVO lastScrapedEarthquake = scrapedEarthquakes.get(0);
        
        final Earthquake lastEarthquake = earthquakeManager.findEarthquakeByDateAndPosition(lastScrapedEarthquake.getFechaLocal(), lastScrapedEarthquake.getLatitud(), lastScrapedEarthquake.getLongitud(), lastScrapedEarthquake.getProfundidad());

        if (lastEarthquake == null) {
            for (Integer i = 0; i < scrapedEarthquakes.size(); ++i) {
                
                EarthquakeVO temp = scrapedEarthquakes.get(i);
                Earthquake earthquakeQuery = earthquakeManager.findEarthquakeByDateAndPosition(temp.getFechaLocal(), temp.getLatitud(), temp.getLongitud(), temp.getProfundidad());
                
                if (earthquakeQuery == null) {
                    Earthquake earthquake = new Earthquake();
                    earthquake.setFechaLocal(temp.getFechaLocal());
                    earthquake.setFechaUtc(temp.getFechaUtc());
                    earthquake.setLatitud(temp.getLatitud());
                    earthquake.setLongitud(temp.getLongitud());
                    earthquake.setProfundidad(temp.getProfundidad());
                    earthquake.setMagnitud_local(temp.getMagnitud_local());
                    earthquake.setAgencia(temp.getAgencia());
                    earthquake.setRef(temp.getRef());
                    earthquakeManager.insertEarthquake(earthquake);
                }
                else {
                    break;
                }
            }
        }
        
        List<Earthquake> earthquakes = earthquakeManager.findAllEarthquakes();
        List<EarthquakeVO> result = new ArrayList<>();

        for (Earthquake earthquake : earthquakes) {
            result.add(new EarthquakeVO(earthquake));
        }

        earthquakes.clear();

        return ResponseEntity.ok(result);
    }

}
