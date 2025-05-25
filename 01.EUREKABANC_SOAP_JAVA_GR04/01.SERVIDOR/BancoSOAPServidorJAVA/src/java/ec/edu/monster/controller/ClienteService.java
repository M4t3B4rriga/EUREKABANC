/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.monster.controller;
import ec.edu.monster.model.Cliente;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.annotation.Resource;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import ec.edu.monster.config.DBConexion;


/**
 *
 * @author sebas
 */
@WebService
public class ClienteService {
    @WebMethod
    public String registrarCliente(Cliente cliente) {
        String sql = "INSERT INTO cliente(chr_cliecodigo, vch_cliepaterno, vch_cliematerno, vch_clienombre, chr_cliedni, vch_clieciudad, vch_cliedireccion, vch_clietelefono, vch_clieemail) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cliente.getCodigo());
            ps.setString(2, cliente.getPaterno());
            ps.setString(3, cliente.getMaterno());
            ps.setString(4, cliente.getNombre());
            ps.setString(5, cliente.getDni());
            ps.setString(6, cliente.getCiudad());
            ps.setString(7, cliente.getDireccion());
            ps.setString(8, cliente.getTelefono());
            ps.setString(9, cliente.getEmail());

            ps.executeUpdate();
            return "Cliente registrado correctamente.";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error al registrar cliente. " + e.getMessage();
        }
    }

    @WebMethod
    public List<Cliente> listarClientes() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM cliente";
        try (Connection con = DBConexion.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setCodigo(rs.getString("chr_cliecodigo"));
                c.setPaterno(rs.getString("vch_cliepaterno"));
                c.setMaterno(rs.getString("vch_cliematerno"));
                c.setNombre(rs.getString("vch_clienombre"));
                c.setDni(rs.getString("chr_cliedni"));
                c.setCiudad(rs.getString("vch_clieciudad"));
                c.setDireccion(rs.getString("vch_cliedireccion"));
                c.setTelefono(rs.getString("vch_clietelefono"));
                c.setEmail(rs.getString("vch_clieemail"));
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
            if (con != null && !con.isClosed()) {
                return "Conexión a la BD exitosa";
            } else {
                return "No se pudo establecer conexión";
            }
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
