package cl.utem.cpyd.z.ProyectoRest.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

import javax.persistence.Id;


@Entity
@Table(name = "usuario")
public class usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario", nullable = false)
    private Long id_usuario = null;

    @Column(name = "token", nullable = false, unique = true)
    private String token = null;

    @Column(name = "app", nullable = false)
    private String app = null;

    @Column(name = "password", nullable = false)
    private String password = null;

    @Column(name = "validacion", nullable = false)
    private boolean validacion = false;

    @Column(name = "createdAt", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    
    public Long getId_usuario() { 
        return this.id_usuario;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getApp() {
        return this.app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getValidacion() {
        return this.validacion;
    }

    public void setValidacion(boolean validacion) {
        this.validacion = validacion;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }
}
