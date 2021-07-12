package cl.utem.infb8090.z.ProyectoRest.etc;


import java.util.List;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import cl.utem.infb8090.z.ProyectoRest.value_objects.EarthquakeVO;


@Service
public class ProyectoRestScrapper implements Serializable {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ProyectoRestJwt.class);
    
    public List<EarthquakeVO> getLastEarthquakes() {

        List<EarthquakeVO> earthquakes = null;
        
        final String url = "http://www.sismologia.cl/links/ultimos_sismos.html";
        
        try {

            final Document documentoHTML = Jsoup.connect(url).get();

            Integer numeroFila = 0;

            for (Element table : documentoHTML.select("table")) {
                
                earthquakes = new ArrayList<EarthquakeVO>();
                
                for (Element row : table.select("tr")) {
                    
                    if (numeroFila > 0) {

                        LocalDateTime fecha_local = null;
                        ZonedDateTime fecha_utc = null;
                        Double latitud = null;
                        Double longitud = null;
                        Integer profundidad = null;
                        Double magnitud_local = null;
                        String agencia = null;
                        String referencia = null;

                        for (Integer numeroColumna = 1; numeroColumna < 9; numeroColumna++) {
                            
                            final String columna = row.select(String.format("td:nth-of-type(%d)", numeroColumna)).text();
                            
                            if (numeroColumna == 1) {
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                                fecha_local = LocalDateTime.parse(columna, formatter);
                            }

                            if (numeroColumna == 2) {
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ssz");
                                fecha_utc = ZonedDateTime.parse(columna+"Z", formatter);
                            }

                            if (numeroColumna == 3) {
                                latitud = Double.parseDouble(columna);
                            }

                            if (numeroColumna == 4) {
                               longitud = Double.parseDouble(columna);
                            }

                            if (numeroColumna == 5) {
                                profundidad = (int)Math.round(Double.parseDouble(columna));
                            }

                            if (numeroColumna == 6) {
                                magnitud_local = Double.parseDouble(columna.substring(0, columna.length()-2));
                            }

                            if (numeroColumna == 7) {
                                agencia = columna;
                            }

                            if (numeroColumna == 8) {
                                referencia = columna;
                            }

                        }
                        
                        final EarthquakeVO earthquake = new EarthquakeVO(fecha_local, fecha_utc, latitud, longitud, profundidad, magnitud_local, agencia, referencia);

                        earthquakes.add(earthquake);
                    }

                    ++numeroFila;
                }
            }

        } catch (Exception e) {
            
            LOGGER.error("Error al conectarse a {} : {}", url, e.getMessage());
            LOGGER.debug("Error al conectarse a {} : {}", url, e.getMessage(), e);
        }


        return earthquakes;
    }
}
