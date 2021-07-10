package cl.utem.infb8090.z.ProyectoRest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.utem.infb8090.z.ProyectoRest.models.Sismo;

@Repository
public interface SismoRepo extends JpaRepository<Sismo, Long> {
    
}
