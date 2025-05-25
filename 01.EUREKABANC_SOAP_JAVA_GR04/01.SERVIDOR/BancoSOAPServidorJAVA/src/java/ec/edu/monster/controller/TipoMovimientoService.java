/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.monster.controller;
import ec.edu.monster.config.DBConexion;
import ec.edu.monster.model.TipoMovimiento;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author sebas
 */
public class TipoMovimientoService {
    @WebMethod
    public String registrarTipoMovimiento(TipoMovimiento tm) {
        String sql = "INSERT INTO TipoMovimiento (chr_tipocodigo, vch_tipodescripcion, vch_tipoaccion, vch_tipoestado) VALUES (?, ?, ?, ?)";
        try (Connection con = DBConexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, tm.getCodigo());
            ps.setString(2, tm.getDescripcion());
            ps.setString(3, tm.getAccion());
            ps.setString(4, tm.getEstado());

            ps.executeUpdate();
            return "Tipo de movimiento registrado correctamente.";
        } catch (SQLException ex) {
            ex.printStackTrace();
            return "Error al registrar tipo de movimiento: " + ex.getMessage();
        }
    }

    @WebMethod
    public List<TipoMovimiento> listarTiposMovimiento() {
        List<TipoMovimiento> lista = new ArrayList<>();
        String sql = "SELECT * FROM TipoMovimiento";
        try (Connection con = DBConexion.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                TipoMovimiento tm = new TipoMovimiento();
                tm.setCodigo(rs.getString("chr_tipocodigo"));
                tm.setDescripcion(rs.getString("vch_tipodescripcion"));
                tm.setAccion(rs.getString("vch_tipoaccion"));
                tm.setEstado(rs.getString("vch_tipoestado"));
                lista.add(tm);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }
}
