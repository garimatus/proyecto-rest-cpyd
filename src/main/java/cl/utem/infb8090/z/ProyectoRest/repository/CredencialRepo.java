package cl.utem.infb8090.z.ProyectoRest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.utem.infb8090.z.ProyectoRest.models.Credencial;

@Repository
public interface CredencialRepo extends JpaRepository<Credencial, Long> {
    
    public Credencial findByAppIgnoreCaseAndPassword(final String app, final String password);

    public Credencial findByAppIgnoreCase(final String app);
}
