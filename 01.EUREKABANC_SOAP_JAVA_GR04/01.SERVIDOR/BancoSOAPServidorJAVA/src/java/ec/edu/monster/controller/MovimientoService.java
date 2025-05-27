/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.monster.controller;

import ec.edu.monster.config.DBConexion;
import ec.edu.monster.model.Movimiento;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
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
    public void registrarMovimiento(Connection con, String cuenta, String tipo, double monto, String empleado, String referencia) throws SQLException {
        String sql = "INSERT INTO movimiento(chr_cuencodigo, int_movinumero, dtt_movifecha, chr_emplcodigo, chr_tipocodigo, dec_moviimporte, chr_cuenreferencia) "
                + "VALUES (?, ?, CURRENT_DATE, ?, ?, ?, ?)";

        // Obtener el siguiente número de movimiento
        String countSql = "SELECT IFNULL(MAX(int_movinumero), 0) + 1 FROM movimiento WHERE chr_cuencodigo = ?";
        int numero;
        try (PreparedStatement psCount = con.prepareStatement(countSql)) {
            psCount.setString(1, cuenta);
            ResultSet rs = psCount.executeQuery();
            rs.next();
            numero = rs.getInt(1);
        }

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cuenta);
            ps.setInt(2, numero);
            ps.setString(3, empleado);
            ps.setString(4, tipo);
            ps.setDouble(5, monto);
            ps.setString(6, referencia); // Puede ser null
            ps.executeUpdate();
        }
    }

    @WebMethod
    public List<Movimiento> listarMovimientosPorCuenta(String cuentaCodigo) {
        List<Movimiento> lista = new ArrayList<>();
        String sql = "SELECT m.*, tm.vch_tipodescripcion, tm.vch_tipoaccion "
                + "FROM movimiento m "
                + "JOIN tipomovimiento tm ON m.chr_tipocodigo = tm.chr_tipocodigo "
                + "WHERE m.chr_cuencodigo = ? "
                + "ORDER BY m.dtt_movifecha DESC, m.int_movinumero DESC";
        try (Connection con = DBConexion.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, cuentaCodigo);
            ResultSet rs = ps.executeQuery();

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
