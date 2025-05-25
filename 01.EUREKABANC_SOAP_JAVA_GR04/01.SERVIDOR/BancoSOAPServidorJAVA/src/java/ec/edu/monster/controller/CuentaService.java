/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.monster.controller;

/**
 *
 * @author sebas
 */

import ec.edu.monster.config.DBConexion;
import ec.edu.monster.model.Cuenta;
import ec.edu.monster.model.Cuenta;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.annotation.Resource;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@WebService
public class CuentaService {
    
    @WebMethod
    public String registrarCuenta(Cuenta cuenta) {
        String sql = "INSERT INTO cuenta(chr_cuencodigo, chr_monecodigo, chr_sucucodigo, chr_emplcreacuenta, chr_cliecodigo, dec_cuensaldo, dtt_cuenfechacreacion, vch_cuenestado, int_cuencontmov, chr_cuenclave) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, cuenta.getCodigo());
            ps.setString(2, cuenta.getMoneda());
            ps.setString(3, cuenta.getSucursal());
            ps.setString(4, cuenta.getEmpleado());
            ps.setString(5, cuenta.getCliente());
            ps.setDouble(6, cuenta.getSaldo());
            ps.setDate(7, Date.valueOf(cuenta.getFechaCreacion())); // yyyy-MM-dd
            ps.setString(8, cuenta.getEstado());
            ps.setInt(9, cuenta.getContMovimientos());
            ps.setString(10, cuenta.getClave());

            ps.executeUpdate();
            return "Cuenta registrada correctamente.";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error al registrar cuenta. " + e.getMessage();
        }
    }

    @WebMethod
    public List<Cuenta> listarCuentas() {
        List<Cuenta> lista = new ArrayList<>();
        String sql = "SELECT * FROM cuenta";
        try (Connection con = DBConexion.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Cuenta c = new Cuenta();
                c.setCodigo(rs.getString("chr_cuencodigo"));
                c.setMoneda(rs.getString("chr_monecodigo"));
                c.setSucursal(rs.getString("chr_sucucodigo"));
                c.setEmpleado(rs.getString("chr_emplcreacuenta"));
                c.setCliente(rs.getString("chr_cliecodigo"));
                c.setSaldo(rs.getDouble("dec_cuensaldo"));
                c.setFechaCreacion(rs.getDate("dtt_cuenfechacreacion").toString());
                c.setEstado(rs.getString("vch_cuenestado"));
                c.setContMovimientos(rs.getInt("int_cuencontmov"));
                c.setClave(rs.getString("chr_cuenclave"));
                lista.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @WebMethod
    public String probarConexion() {
        try (Connection con = DBConexion.getConnection()) {
            return con != null && !con.isClosed() ? "Conexión a BD exitosa." : "Conexión fallida.";
        } catch (SQLException e) {
            return "Error: " + e.getMessage();
        }
    }
}


