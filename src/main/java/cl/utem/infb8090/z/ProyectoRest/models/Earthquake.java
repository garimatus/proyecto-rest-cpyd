package cl.utem.infb8090.z.ProyectoRest.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;


@Entity
@Table(name = "sismo_z")
public class Earthquake {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @Column(name = "fecha_local", nullable = false)
    private LocalDateTime fecha_local = null;

    @Column(name = "fecha_utc", nullable = false)
    private ZonedDateTime fecha_utc = null;

    @Column(name = "latitud", nullable = false)
    private Double latitud = null;

    @Column(name = "longitud", nullable = false)
    private Double longitud = null;

    @Column(name = "profundidad", nullable = false)
    private Integer profundidad = null;

    @Column(name = "magnitud_local", nullable = false)
    private Double magnitud_local = null;

    @Column(name = "agencia", nullable = false)
    private String agencia = null;

    @Column(name = "ref_geografica", nullable = false)
    private String ref_geografica = null;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime created_at = LocalDateTime.now();
    
    public Long getId() {
        return this.id;
    }

    public LocalDateTime getFechaLocal() {
        return this.fecha_local;
    }

    public void setFechaLocal(LocalDateTime fecha) {
        this.fecha_local = fecha;
    }

    public ZonedDateTime getFechaUtc() {
        return this.fecha_utc;
    }

    public void setFechaUtc(ZonedDateTime fecha) {
        this.fecha_utc = fecha;
    }

    public Double getLatitud() {
        return this.latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return this.longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public Integer getProfundidad() {
        return this.profundidad;
    }

    public void setProfundidad(Integer profundidad) {
        this.profundidad = profundidad;
    }

    public Double getMagnitud_local() {
        return this.magnitud_local;
    }

    public void setMagnitud_local(Double magnitud) {
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
        return this.created_at;
    }
}
