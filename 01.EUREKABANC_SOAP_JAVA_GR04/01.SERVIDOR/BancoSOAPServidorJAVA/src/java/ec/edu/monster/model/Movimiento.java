package ec.edu.monster.model;
<<<<<<< HEAD
import java.util.Date;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlRootElement;
=======

import java.time.LocalDateTime;

>>>>>>> f314e0f09a32b7739f700b8e03fe9c5690fb3ec1
/**
 *
 * @author sebas
 */
@XmlRootElement(name = "Movimiento")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
    "cuentaCodigo",
    "numeroMovimiento",
    "fecha",
    "empleadoCodigo",
    "tipoCodigo",
    "importe",
    "cuentaReferencia"
})
public class Movimiento {
    private String cuentaCodigo;       // chr_cuencodigo
    private int numeroMovimiento;      // int_movinumero
    private LocalDateTime fecha;       // dtt_movifecha (ahora con fecha y hora)
    private String empleadoCodigo;     // chr_emplcodigo
    private String tipoCodigo;         // chr_tipocodigo
    private String descripcionTipo;    // vch_tipodescripcion (nuevo campo)
    private String accionTipo;         // vch_tipoaccion (nuevo campo)
    private double importe;            // dec_moviimporte
    private String cuentaReferencia;   // chr_cuenreferencia

    
    public Movimiento() {}

    // Getters y Setters
    
    public String getCuentaCodigo() {
        return cuentaCodigo;
    }

    public void setCuentaCodigo(String cuentaCodigo) {
        this.cuentaCodigo = cuentaCodigo;
    }

    public int getNumeroMovimiento() {
        return numeroMovimiento;
    }

    public void setNumeroMovimiento(int numeroMovimiento) {
        this.numeroMovimiento = numeroMovimiento;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
    
    // Método adicional para obtener fecha como String (opcional)
    public String getFechaString() {
        return fecha != null ? fecha.toString() : "";
    }

    public String getEmpleadoCodigo() {
        return empleadoCodigo;
    }

    public void setEmpleadoCodigo(String empleadoCodigo) {
        this.empleadoCodigo = empleadoCodigo;
    }

    public String getTipoCodigo() {
        return tipoCodigo;
    }

    public void setTipoCodigo(String tipoCodigo) {
        this.tipoCodigo = tipoCodigo;
    }

    public String getDescripcionTipo() {
        return descripcionTipo;
    }

    public void setDescripcionTipo(String descripcionTipo) {
        this.descripcionTipo = descripcionTipo;
    }

    public String getAccionTipo() {
        return accionTipo;
    }

    public void setAccionTipo(String accionTipo) {
        this.accionTipo = accionTipo;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public String getCuentaReferencia() {
        return cuentaReferencia;
    }

    public void setCuentaReferencia(String cuentaReferencia) {
        this.cuentaReferencia = cuentaReferencia;
    }
}