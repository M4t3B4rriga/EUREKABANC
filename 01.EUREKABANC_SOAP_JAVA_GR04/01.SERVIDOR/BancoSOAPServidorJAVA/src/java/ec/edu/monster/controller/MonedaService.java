/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.monster.controller;
import ec.edu.monster.config.DBConexion;
import ec.edu.monster.model.Moneda;
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
public class MonedaService {
      @WebMethod
    public List<Moneda> listarMonedas() {
        List<Moneda> lista = new ArrayList<>();
        String sql = "SELECT * FROM moneda";
        try (Connection con = DBConexion.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Moneda m = new Moneda();
                m.setCodigo(rs.getString("chr_monecodigo"));
                m.setDescripcion(rs.getString("vch_monedescripcion"));
                lista.add(m);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
