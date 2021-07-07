package cl.utem.cpyd.z.ProyectoRest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.utem.cpyd.z.ProyectoRest.models.usuario;

@Repository
public interface usuarioRepo extends JpaRepository<usuario, Long> {
    
}
