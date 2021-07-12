package cl.utem.infb8090.z.ProyectoRest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.JpaQueryCreator;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.time.LocalDateTime;

import cl.utem.infb8090.z.ProyectoRest.models.Earthquake;

@Repository
public interface EarthquakeRepo extends JpaRepository<Earthquake, Long> {
    
    @Query(value = "SELECT * FROM sismo_z", nativeQuery = true)
    public List<Earthquake> findAllEarthquakes();

    @Query(value = "SELECT * FROM sismo_z WHERE fecha_local = :fecha AND latitud = :latitud AND longitud = :longitud AND profundidad = :profundidad", nativeQuery = true)
    public Earthquake findEarthquakeByDateAndPosition(@Param("fecha") LocalDateTime fecha, @Param("latitud") Double latitud, @Param("longitud") Double longitud, @Param("profundidad") Integer profundidad);
}
