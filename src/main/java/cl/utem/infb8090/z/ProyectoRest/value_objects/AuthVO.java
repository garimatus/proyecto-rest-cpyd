package cl.utem.infb8090.z.ProyectoRest.value_objects;

import java.io.Serializable;

public class AuthVO implements Serializable {
    
    private String bearer = null;

    public AuthVO() {
    }

    public AuthVO(String bearer) {
        this.bearer = bearer;
    }
    
    public String getBearer() {
        return this.bearer;
    }

    public void getBearer(String bearer) {
        this.bearer = bearer;
    }
}
