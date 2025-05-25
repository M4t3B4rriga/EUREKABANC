/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.monster.controller;
import ec.edu.monster.config.DBConexion;
import ec.edu.monster.model.Sucursal;
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
public class SucursalService {
    @WebMethod
    public List<Sucursal> listarSucursales() {
        List<Sucursal> lista = new ArrayList<>();
        String sql = "SELECT * FROM sucursal";
        try (Connection con = DBConexion.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Sucursal s = new Sucursal();
                s.setCodigo(rs.getString("chr_sucucodigo"));
                s.setNombre(rs.getString("vch_sucunombre"));
                s.setCiudad(rs.getString("vch_sucuciudad"));
                s.setDireccion(rs.getString("vch_sucudireccion"));
                s.setContadorCuenta(rs.getInt("int_sucucontcuenta"));
                lista.add(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
