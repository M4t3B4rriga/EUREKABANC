/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.monster.controller;
import ec.edu.monster.config.DBConexion;
import ec.edu.monster.model.Empleado;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.annotation.Resource;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author sebas
 */

@WebService
public class EmpleadoService {
   @WebMethod
    public String registrarEmpleado(Empleado e) {
        String sql = "INSERT INTO empleado(chr_emplcodigo, vch_emplpaterno, vch_emplmaterno, vch_emplnombre, vch_emplciudad, vch_empldireccion) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, e.getCodigo());
            ps.setString(2, e.getPaterno());
            ps.setString(3, e.getMaterno());
            ps.setString(4, e.getNombre());
            ps.setString(5, e.getCiudad());
            ps.setString(6, e.getDireccion());

            ps.executeUpdate();
            return "Empleado registrado correctamente.";
        } catch (SQLException ex) {
            ex.printStackTrace();
            return "Error al registrar empleado. " + ex.getMessage();
        }
    }

    @WebMethod
    public List<Empleado> listarEmpleados() {
        List<Empleado> lista = new ArrayList<>();
        String sql = "SELECT * FROM empleado";
        try (Connection con = DBConexion.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Empleado e = new Empleado();
                e.setCodigo(rs.getString("chr_emplcodigo"));
                e.setPaterno(rs.getString("vch_emplpaterno"));
                e.setMaterno(rs.getString("vch_emplmaterno"));
                e.setNombre(rs.getString("vch_emplnombre"));
                e.setCiudad(rs.getString("vch_emplciudad"));
                e.setDireccion(rs.getString("vch_empldireccion"));
                lista.add(e);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }
}
