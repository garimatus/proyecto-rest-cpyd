package cl.utem.infb8090.z.ProyectoRest.repository_manager;


import java.io.Serializable;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import cl.utem.infb8090.z.ProyectoRest.models.Credencial;
import cl.utem.infb8090.z.ProyectoRest.repository.CredencialRepo;


@Service
public class CredencialManager implements Serializable {
    
    @Autowired
    private transient CredencialRepo credencialRepo;

    public Credencial getCredencialByAppAndPassword(final String app, final String password) {
        
        Credencial credencial = null;

        credencial = credencialRepo.findByAppIgnoreCaseAndPassword(app, password);

        return credencial;
    }

    public Credencial getCredencialByApp(final String app) {
        
        Credencial credencial = null;

        credencial = credencialRepo.findByAppIgnoreCase(app);

        return credencial;
    }
}
