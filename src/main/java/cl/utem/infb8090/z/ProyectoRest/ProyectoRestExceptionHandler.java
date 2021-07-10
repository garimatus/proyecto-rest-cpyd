package cl.utem.infb8090.z.ProyectoRest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.utem.infb8090.z.ProyectoRest.exceptions.ProyectoRestException;
import cl.utem.infb8090.z.ProyectoRest.value_objects.ExceptionVO;


@ControllerAdvice(basePackages = {"cl.utem.infb8090.z.ProyectoRest.handlers"})
public class ProyectoRestExceptionHandler {

    public static final Logger LOGGER = LoggerFactory.getLogger(ProyectoRestExceptionHandler.class);

    @ExceptionHandler({Exception.class})
    public ResponseEntity handleException(ProyectoRestException e) {

        LOGGER.error("Se ha atrapado un error en la ejecución : {}", e.getMessage());
        LOGGER.debug("Se ha atrapado un error en la ejecución : {}", e.getMessage(), e);

        final HttpStatus error = HttpStatus.INTERNAL_SERVER_ERROR;
        final ExceptionVO exception = new ExceptionVO(e.getMessage());
        
        return new ResponseEntity(exception, error);
    }
}
