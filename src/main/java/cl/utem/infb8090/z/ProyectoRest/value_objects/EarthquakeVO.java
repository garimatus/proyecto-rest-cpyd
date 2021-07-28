package cl.utem.infb8090.z.ProyectoRest.value_objects;


import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import cl.utem.infb8090.z.ProyectoRest.models.Earthquake;
import io.swagger.annotations.ApiModel;

@ApiModel(value = "Earthquake")
public class EarthquakeVO {

    private LocalDateTime fecha_local = null;

    private ZonedDateTime fecha_utc = null;

    private Double latitud = null;

    private Double longitud = null;

    private Integer profundidad = null;

    private Double magnitud_local = null;

    private String agencia = null;

    private String ref_geografica = null;

    public EarthquakeVO(LocalDateTime fecha, ZonedDateTime fecha_utc, Double latitud, Double longitud, Integer profundidad, Double magnitud, String agencia, String referencia) {
        this.fecha_local = fecha;
        this.fecha_utc = fecha_utc;
        this.latitud = latitud;
        this.longitud = longitud;
        this.profundidad = profundidad;
        this.magnitud_local = magnitud;
        this.agencia = agencia;
        this.ref_geografica = referencia;
    }

    public EarthquakeVO(Earthquake earthquake) {
        this.fecha_local = earthquake.getFechaLocal();
        this.fecha_utc = earthquake.getFechaUtc();
        this.latitud = earthquake.getLatitud();
        this.longitud = earthquake.getLongitud();
        this.profundidad = earthquake.getProfundidad();
        this.magnitud_local = earthquake.getMagnitud_local();
        this.agencia = earthquake.getAgencia();
        this.ref_geografica = earthquake.getRef();
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
}
