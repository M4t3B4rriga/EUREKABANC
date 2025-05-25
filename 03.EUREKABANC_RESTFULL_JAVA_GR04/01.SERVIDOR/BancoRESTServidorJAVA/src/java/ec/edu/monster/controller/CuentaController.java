/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.monster.controller;
import ec.edu.monster.config.DBConexion;
import ec.edu.monster.model.Cuenta;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.*;
/**
 *
 * @author sebas
 */
@Path("/cuenta")
public class CuentaController {
    @GET
    @Path("/saldo/{codigo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerSaldo(@PathParam("codigo") String codigo) {
        String sql = "SELECT dec_cuensaldo FROM cuenta WHERE chr_cuencodigo = ?";
        try (Connection con = DBConexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, codigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Cuenta c = new Cuenta();
                c.setCodigo(codigo);
                c.setSaldo(rs.getDouble("dec_cuensaldo"));
                return Response.ok(c).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (SQLException e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/depositar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response depositar(Cuenta cuenta) {
        String sql = "UPDATE cuenta SET dec_cuensaldo = dec_cuensaldo + ? WHERE chr_cuencodigo = ?";
        try (Connection con = DBConexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDouble(1, cuenta.getSaldo());
            ps.setString(2, cuenta.getCodigo());
            int rows = ps.executeUpdate();
            return (rows > 0) ? Response.ok("Depósito exitoso").build()
                              : Response.status(Response.Status.NOT_FOUND).entity("Cuenta no encontrada").build();
        } catch (SQLException e) {
            return Response.serverError().entity("Error: " + e.getMessage()).build();
        }
    }

    @POST
    @Path("/retirar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response retirar(Cuenta cuenta) {
        String sqlSelect = "SELECT dec_cuensaldo FROM cuenta WHERE chr_cuencodigo = ?";
        String sqlUpdate = "UPDATE cuenta SET dec_cuensaldo = dec_cuensaldo - ? WHERE chr_cuencodigo = ?";
        try (Connection con = DBConexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sqlSelect)) {
            ps.setString(1, cuenta.getCodigo());
            ResultSet rs = ps.executeQuery();
            if (rs.next() && rs.getDouble(1) >= cuenta.getSaldo()) {
                PreparedStatement ps2 = con.prepareStatement(sqlUpdate);
                ps2.setDouble(1, cuenta.getSaldo());
                ps2.setString(2, cuenta.getCodigo());
                ps2.executeUpdate();
                return Response.ok("Retiro exitoso").build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).entity("Saldo insuficiente o cuenta inválida").build();
            }
        } catch (SQLException e) {
            return Response.serverError().entity("Error: " + e.getMessage()).build();
        }
    }

    @POST
    @Path("/transferir")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response transferir(@QueryParam("origen") String origen,
                               @QueryParam("destino") String destino,
                               @QueryParam("monto") double monto) {
        try (Connection con = DBConexion.getConnection()) {
            con.setAutoCommit(false);
            // Verificar saldo en origen
            PreparedStatement sel = con.prepareStatement("SELECT dec_cuensaldo FROM cuenta WHERE chr_cuencodigo = ?");
            sel.setString(1, origen);
            ResultSet rs = sel.executeQuery();
            if (!rs.next() || rs.getDouble(1) < monto) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Saldo insuficiente").build();
            }
            // Actualizar origen
            PreparedStatement restar = con.prepareStatement("UPDATE cuenta SET dec_cuensaldo = dec_cuensaldo - ? WHERE chr_cuencodigo = ?");
            restar.setDouble(1, monto);
            restar.setString(2, origen);
            restar.executeUpdate();
            // Actualizar destino
            PreparedStatement sumar = con.prepareStatement("UPDATE cuenta SET dec_cuensaldo = dec_cuensaldo + ? WHERE chr_cuencodigo = ?");
            sumar.setDouble(1, monto);
            sumar.setString(2, destino);
            sumar.executeUpdate();

            con.commit();
            return Response.ok("Transferencia exitosa").build();
        } catch (SQLException e) {
            return Response.serverError().entity("Error: " + e.getMessage()).build();
        }
    }
}
