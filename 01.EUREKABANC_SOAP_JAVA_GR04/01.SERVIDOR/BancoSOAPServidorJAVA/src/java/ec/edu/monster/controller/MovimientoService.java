/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.monster.controller;
import ec.edu.monster.config.DBConexion;
import ec.edu.monster.model.Movimiento;
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
public class MovimientoService {
    @WebMethod
    public String registrarMovimiento(Movimiento mov) {
        if (mov == null || mov.getCuentaCodigo() == null || mov.getCuentaCodigo().isEmpty()) {
        throw new IllegalArgumentException("El código de cuenta es obligatorio");
    }
        String sql = "INSERT INTO movimiento(chr_cuencodigo, int_movinumero, dtt_movifecha, chr_emplcodigo, chr_tipocodigo, dec_moviimporte, chr_cuenreferencia) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, mov.getCuentaCodigo());
            ps.setInt(2, mov.getNumeroMovimiento());
            // Validar formato de fecha
            try {
                if (mov.getFecha() == null || mov.getFecha().isEmpty()) {
                    return "Error: La fecha no puede ser nula o vacía.";
                }
                Date fechaSql = Date.valueOf(mov.getFecha()); // requiere formato yyyy-MM-dd
                ps.setDate(3, fechaSql);
            } catch (IllegalArgumentException e) {
                return "Error: Formato de fecha inválido. Se requiere yyyy-MM-dd. Detalle: " + e.getMessage();
                }
           
            ps.setString(4, mov.getEmpleadoCodigo());
            ps.setString(5, mov.getTipoCodigo());
            ps.setDouble(6, mov.getImporte());
            ps.setString(7, mov.getCuentaReferencia());

            ps.executeUpdate();
            return "Movimiento registrado correctamente.";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error al registrar movimiento. " + e.getMessage();
        }
    }

    @WebMethod
    public List<Movimiento> listarMovimientos() {
        List<Movimiento> lista = new ArrayList<>();
        String sql = "SELECT * FROM movimiento";
        try (Connection con = DBConexion.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Movimiento m = new Movimiento();
                m.setCuentaCodigo(rs.getString("chr_cuencodigo"));
                m.setNumeroMovimiento(rs.getInt("int_movinumero"));
                m.setFecha(rs.getDate("dtt_movifecha").toString());
                m.setEmpleadoCodigo(rs.getString("chr_emplcodigo"));
                m.setTipoCodigo(rs.getString("chr_tipocodigo"));
                m.setImporte(rs.getDouble("dec_moviimporte"));
                m.setCuentaReferencia(rs.getString("chr_cuenreferencia"));
                lista.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
