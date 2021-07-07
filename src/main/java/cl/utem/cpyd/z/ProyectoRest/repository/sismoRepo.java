package cl.utem.cpyd.z.ProyectoRest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.utem.cpyd.z.ProyectoRest.models.sismo;

@Repository
public interface sismoRepo extends JpaRepository<sismo, Long> {
    
}
