package cl.utem.infb8090.z.ProyectoRest.value_objects;

public class ExceptionVO {
    
    private final String message;

    public ExceptionVO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
