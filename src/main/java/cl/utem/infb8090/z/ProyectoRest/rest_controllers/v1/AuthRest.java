package cl.utem.infb8090.z.ProyectoRest.rest_controllers.v1;


import java.io.Serializable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


import cl.utem.infb8090.z.ProyectoRest.etc.ProyectoRestJwt;
import cl.utem.infb8090.z.ProyectoRest.exceptions.ProyectoRestException;
import cl.utem.infb8090.z.ProyectoRest.models.Credencial;
import cl.utem.infb8090.z.ProyectoRest.repository_manager.CredencialManager;
import cl.utem.infb8090.z.ProyectoRest.value_objects.LoginVO;
import cl.utem.infb8090.z.ProyectoRest.value_objects.AuthVO;
import cl.utem.infb8090.z.ProyectoRest.value_objects.ExceptionVO;


@RestController
@RequestMapping(value = {"/v1/auth"}, consumes = {"application/json;charset=utf-8"}, produces = {"application/json;charset=utf-8"})
public class AuthRest implements Serializable {

    public static final Logger LOGGER = LoggerFactory.getLogger(AuthRest.class);

    @Autowired
    private transient CredencialManager credencialManager;

    @Autowired
    private transient ProyectoRestJwt proyectoRestJwt;

    @ApiOperation(value = "Solicitar un token JWT válido para consumir la operación del servicio REST")
    @PostMapping(value = {"/login"}, consumes = {"application/json;charset=utf-8"}, produces = {"application/json;charset=utf-8"})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Respuesta exitosa", response = AuthVO.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "Acceso denegado", response = ExceptionVO.class),
        @ApiResponse(code = 403, message = "Sin permisos suficientes", response = ExceptionVO.class),
        @ApiResponse(code = 404, message = "Recurso no encontrado", response = ExceptionVO.class),
        @ApiResponse(code = 412, message = "Precondición fallida", response = ExceptionVO.class)
    })
    public ResponseEntity<AuthVO> login(@RequestBody LoginVO request) {
        
        if (request == null) {
            throw new ProyectoRestException(400, "Petición inválida");
        }

        final String app = StringUtils.trimToEmpty(request.getApp());
        final String password = request.getPassword();
        
        if (StringUtils.isBlank(app) || StringUtils.isBlank(password)) {
            final String message = "Credenciales con datos insuficientes";
            LOGGER.error(message);
            throw new ProyectoRestException(message);
        }
        
        final Credencial credencial = credencialManager.getCredencialByAppAndPassword(app, password);

        if (credencial == null || !credencial.getValidacion()) {
            final String message = "El usuario no existe o no ha sido autorizado";
            LOGGER.error(message);
            throw new ProyectoRestException(401, message);
        }

        final String jwt = proyectoRestJwt.crearJwt("/v1/auth/login", credencial.getToken());

        if (StringUtils.isBlank(jwt)) {
            LOGGER.error("No se pudo generar el token solicitado");
            throw new ProyectoRestException("No se pudo generar el token solicitado");
        }

        return ResponseEntity.ok(new AuthVO(jwt));
    }
}
