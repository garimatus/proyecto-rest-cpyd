package cl.utem.infb8090.z.ProyectoRest.exceptions;

public class ProyectoRestException extends RuntimeException {
    
    private final Integer code;
    
    public ProyectoRestException() {
        super("Error 412 : Precondición fallida.");
        this.code = 412;
    }
    
    public ProyectoRestException(String message) {
        super(message);
        this.code = 412;
    }

    public ProyectoRestException(String message, Throwable cause) {
        super(message, cause);
        this.code = 412;
    }

    public ProyectoRestException(Integer httpCode, String message) {
        super(message);
        this.code = httpCode;
    }
}
