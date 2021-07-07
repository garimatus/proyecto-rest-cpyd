package cl.utem.cpyd.z.ProyectoRest.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.time.LocalDateTime;


@Entity
@Table(name = "sismo")
public class sismo {

    @Id
    @Column(name = "id_sismo", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_sismo = null;

    @Column(name = "fecha_local", nullable = false)
    private LocalDateTime fecha_local = null;

    @Column(name = "latitud", nullable = false)
    private Integer latitud = null;

    @Column(name = "longitud", nullable = false)
    private Integer longitud = null;

    @Column(name = "profundidad", nullable = false)
    private Integer profundidad = null;

    @Column(name = "magnitud_local", nullable = false)
    private Float magnitud_local = null;

    @Column(name = "agencia", nullable = false)
    private String agencia = null;

    @Column(name = "ref_geografica", nullable = false)
    private String ref_geografica = null;

    @Column(name = "createdAt", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    
    public Long getId() {
        return this.id_sismo;
    }

    public LocalDateTime getFechaLocal() {
        return this.fecha_local;
    }

    public void setFechaLocal(LocalDateTime fecha) {
        this.fecha_local = fecha;
    }

    public Integer getLatitud() {
        return this.latitud;
    }

    public void setLatitud(Integer latitud) {
        this.latitud = latitud;
    }

    public Integer getLongitud() {
        return this.longitud;
    }

    public void setLongitud(Integer longitud) {
        this.longitud = longitud;
    }

    public Integer getProfundidad() {
        return this.profundidad;
    }

    public void setProfundidad(Integer profundidad) {
        this.profundidad = profundidad;
    }

    public Float getMagnitud_local() {
        return this.magnitud_local;
    }

    public void setMagnitud_local(Float magnitud) {
        this.magnitud_local = magnitud;
    }

    public String getAgencia() {
        return this.agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getRef() {
        return this.ref_geografica;
    }

    public void setRef(String ref) {
        this.ref_geografica = ref;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }
}
