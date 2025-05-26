package ec.edu.monster.controller;

import ec.edu.monster.config.DBConexion;
import ec.edu.monster.model.Movimiento;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@WebService
public class MovimientoService {
    
    @WebMethod
    public List<Movimiento> listarMovimientosPorCuenta(String cuentaCodigo) {
        List<Movimiento> lista = new ArrayList<>();
        String sql = "SELECT m.*, tm.vch_tipodescripcion, tm.vch_tipoaccion " +
                     "FROM movimiento m " +
                     "JOIN tipomovimiento tm ON m.chr_tipocodigo = tm.chr_tipocodigo " +
                     "WHERE m.chr_cuencodigo = ? " +
                     "ORDER BY m.dtt_movifecha DESC, m.int_movinumero DESC";
        
        try (Connection con = DBConexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, cuentaCodigo);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Movimiento m = new Movimiento();
                m.setCuentaCodigo(rs.getString("chr_cuencodigo"));
                m.setNumeroMovimiento(rs.getInt("int_movinumero"));
                
                // Convertir Timestamp a LocalDateTime
                Timestamp timestamp = rs.getTimestamp("dtt_movifecha");
                m.setFecha(timestamp != null ? timestamp.toLocalDateTime() : null);
                
                m.setEmpleadoCodigo(rs.getString("chr_emplcodigo"));
                m.setTipoCodigo(rs.getString("chr_tipocodigo"));
                m.setDescripcionTipo(rs.getString("vch_tipodescripcion"));
                m.setAccionTipo(rs.getString("vch_tipoaccion"));
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