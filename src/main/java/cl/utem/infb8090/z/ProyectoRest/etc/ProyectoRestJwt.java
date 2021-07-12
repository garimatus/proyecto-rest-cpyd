package cl.utem.infb8090.z.ProyectoRest.etc;

import java.io.Serializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

import cl.utem.infb8090.z.ProyectoRest.models.Credencial;

@Service
public class ProyectoRestJwt implements Serializable {
    
    private static final String secret = "ComputacionParalelayDistribuida";

    private static final Logger LOGGER = LoggerFactory.getLogger(ProyectoRestJwt.class);

    public String crearJwt(final String issuer, final Credencial credencial) {
        
        String jwt = StringUtils.EMPTY;

        try {
            
            if (credencial != null) {

                Algorithm algoritmo = Algorithm.HMAC256(secret);
                Date issuedAt = new Date();
                Date expiresAt = DateUtils.addMinutes(issuedAt, 420);
                
                jwt = JWT.create()
                .withJWTId(credencial.getToken())
                .withIssuer(issuer)
                .withIssuedAt(issuedAt)
                .withExpiresAt(expiresAt)
                .withClaim("id", credencial.getApp())
                .sign(algoritmo);
            }

        } catch (Exception e) {
            LOGGER.error("Error al crear token : {}", e.getMessage());
            LOGGER.debug("Error al crear token : {}", e.getMessage(), e);
        }
        
        return jwt;
    }

    public Boolean validarJwt(final Credencial credencial, final String jwt) {
        Boolean validacion = false;

        try {
            
            if (StringUtils.isNotBlank(jwt) && credencial != null) {
                
                Algorithm algoritmo = Algorithm.HMAC256(secret);
                Verification verificacion = JWT.require(algoritmo);
                JWTVerifier verificador = verificacion.build();
                DecodedJWT decodificacion = verificador.verify(jwt);
                
                if (decodificacion != null) {
                    
                    final String jwtID = StringUtils.trimToEmpty(decodificacion.getClaim("id").asString());

                    validacion = StringUtils.equals(credencial.getApp(), jwtID);

                    if (!validacion) {
                        LOGGER.error("El token no corresponde al usuario");
                    }
                }
            }

        } catch (Exception e) {
            validacion = false;
            LOGGER.error("Error al decodificar token : {}", e.getMessage());
            LOGGER.debug("Error al decodificar token : {}", e.getMessage(), e);
        }
        
        return validacion;
    }
}
