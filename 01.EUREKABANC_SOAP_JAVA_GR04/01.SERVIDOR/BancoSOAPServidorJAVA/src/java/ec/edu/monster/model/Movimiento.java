/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.monster.model;
import java.util.Date;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlRootElement;
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
    private String fecha;              // dtt_movifecha (formato yyyy-MM-dd)
    private String empleadoCodigo;     // chr_emplcodigo
    private String tipoCodigo;         // chr_tipocodigo
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
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
