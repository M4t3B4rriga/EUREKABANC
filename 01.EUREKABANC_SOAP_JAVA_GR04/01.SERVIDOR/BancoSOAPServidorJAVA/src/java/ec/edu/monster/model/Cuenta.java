/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.monster.model;

/**
 *
 * @author sebas
 */
public class Cuenta {
    private String codigo;           // chr_cuencodigo
    private String moneda;           // chr_monecodigo
    private String sucursal;         // chr_sucucodigo
    private String empleado;         // chr_emplcreacuenta
    private String cliente;          // chr_cliecodigo
    private double saldo;            // dec_cuensaldo
    private String fechaCreacion;    // dtt_cuenfechacreacion (como String en formato yyyy-MM-dd)
    private String estado;           // vch_cuenestado
    private int contMovimientos;     // int_cuencontmov
    private String clave;            // chr_cuenclave

    // Getters y Setters
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getMoneda() { return moneda; }
    public void setMoneda(String moneda) { this.moneda = moneda; }

    public String getSucursal() { return sucursal; }
    public void setSucursal(String sucursal) { this.sucursal = sucursal; }

    public String getEmpleado() { return empleado; }
    public void setEmpleado(String empleado) { this.empleado = empleado; }

    public String getCliente() { return cliente; }
    public void setCliente(String cliente) { this.cliente = cliente; }

    public double getSaldo() { return saldo; }
    public void setSaldo(double saldo) { this.saldo = saldo; }

    public String getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(String fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public int getContMovimientos() { return contMovimientos; }
    public void setContMovimientos(int contMovimientos) { this.contMovimientos = contMovimientos; }

    public String getClave() { return clave; }
    public void setClave(String clave) { this.clave = clave; }
}
