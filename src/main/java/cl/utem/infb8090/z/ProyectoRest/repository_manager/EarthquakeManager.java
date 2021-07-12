package cl.utem.infb8090.z.ProyectoRest.repository_manager;


import java.io.Serializable;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.time.LocalDateTime;

import cl.utem.infb8090.z.ProyectoRest.models.Earthquake;
import cl.utem.infb8090.z.ProyectoRest.repository.EarthquakeRepo;


@Service
public class EarthquakeManager implements Serializable {
    
    @Autowired
    private transient EarthquakeRepo earthquakeRepo;
    
    public Earthquake findEarthquakeByDateAndPosition(LocalDateTime fecha, Double latitud, Double longitud, Integer profundidad) {

        Earthquake earthquake = earthquakeRepo.findEarthquakeByDateAndPosition(fecha, latitud, longitud, profundidad);

        return earthquake;
    }
    
    public List<Earthquake> findAllEarthquakes() {

        List<Earthquake> earthquakes = earthquakeRepo.findAllEarthquakes();

        return earthquakes;
    }

    /*public List<Earthquake> getEarthquakesBetweenTwoLocalDateTimes(LocalDateTime f_inicio, LocalDateTime f_termino) {

        List<Earthquake> earthquakes = earthquakeRepo.getEarthquakesBetweenLocalDateTimes();

        return earthquakes;
    }*/

    public void insertEarthquake(Earthquake earthquake) {

        earthquakeRepo.save(earthquake);
    }
}
