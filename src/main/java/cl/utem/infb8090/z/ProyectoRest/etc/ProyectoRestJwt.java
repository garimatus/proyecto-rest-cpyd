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



@Service
public class ProyectoRestJwt implements Serializable {
    
    private static final String secret = "ComputacionParalelayDistribuida";

    private static final Logger LOGGER = LoggerFactory.getLogger(ProyectoRestJwt.class);

    public String crearJwt(final String issuer, final String appToken) {
        
        String jwt = StringUtils.EMPTY;

        try {

            if (StringUtils.isNotBlank(appToken)) {
                Algorithm algoritmo = Algorithm.HMAC256(secret);
                Date issuedAt = new Date();
                Date expiresAt = DateUtils.addMinutes(issuedAt, 420);
                
                jwt = JWT.create()
                .withIssuer(issuer)
                .withIssuedAt(issuedAt)
                .withExpiresAt(expiresAt)
                .withClaim("AppToken", appToken)
                .sign(algoritmo);
            }

        } catch (Exception e) {
            LOGGER.error("Error al crear token : {}", e.getMessage());
            LOGGER.debug("Error al crear token : {}", e.getMessage(), e);
        }
        
        return jwt;
    }

    public Boolean validarJwt(final String jwt, final String appToken) {
        Boolean validacion = false;

        try {
            
            if (StringUtils.isNotBlank(jwt) && StringUtils.isNotBlank(appToken)) {
                
                Algorithm algoritmo = Algorithm.HMAC256(secret);
                Verification verificacion = JWT.require(algoritmo);
                JWTVerifier verificador = verificacion.build();
                DecodedJWT decodificacion = verificador.verify(jwt);
                
                if (decodificacion != null) {
                    
                    final String AppToken = StringUtils.trimToEmpty(decodificacion.getClaim("AppToken").asString());

                    validacion = StringUtils.equals(appToken, AppToken);

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
