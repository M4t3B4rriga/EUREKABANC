package ec.edu.monster.controller;

<<<<<<< HEAD
/**
 *
 * @author sebas
 */
=======
>>>>>>> f314e0f09a32b7739f700b8e03fe9c5690fb3ec1
import ec.edu.monster.config.DBConexion;
import ec.edu.monster.model.Cuenta;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebService
public class CuentaService {

    private final MovimientoService movimientoService = new MovimientoService();

    @WebMethod
    public String registrarCuenta(Cuenta cuenta) {
        String sql = "INSERT INTO cuenta(chr_cuencodigo, chr_monecodigo, chr_sucucodigo, chr_emplcreacuenta, chr_cliecodigo, dec_cuensaldo, dtt_cuenfechacreacion, vch_cuenestado, int_cuencontmov, chr_cuenclave) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConexion.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, cuenta.getCodigo());
            ps.setString(2, cuenta.getMoneda());
            ps.setString(3, cuenta.getSucursal());
            ps.setString(4, cuenta.getEmpleado());
            ps.setString(5, cuenta.getCliente());
            ps.setDouble(6, cuenta.getSaldo());
            ps.setDate(7, Date.valueOf(cuenta.getFechaCreacion()));
            ps.setString(8, cuenta.getEstado());
            ps.setInt(9, cuenta.getContMovimientos());
            ps.setString(10, cuenta.getClave());

            ps.executeUpdate();
            
            // Registrar movimiento de apertura de cuenta
            registrarMovimiento(cuenta.getCodigo(), "001", cuenta.getSaldo(), null, cuenta.getEmpleado());
            
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
        try (Connection con = DBConexion.getConnection(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
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
    public String depositar(String cuentaCodigo, double monto) {
<<<<<<< HEAD
        if (monto <= 0) {
            return "Monto inválido";
        }
        String sql = "UPDATE cuenta SET dec_cuensaldo = dec_cuensaldo + ? WHERE chr_cuencodigo = ?";
        try (Connection con = DBConexion.getConnection()) {
            con.setAutoCommit(false);

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setDouble(1, monto);
                ps.setString(2, cuentaCodigo);
                int rows = ps.executeUpdate();
                if (rows > 0) {
                    movimientoService.registrarMovimiento(con, cuentaCodigo, "003", monto, "0001", null); // tipo "003" = DEPÓSITO, empleado fijo
                    con.commit();
                    return "Depósito exitoso";
                } else {
                    con.rollback();
                    return "Cuenta no encontrada";
                }
            }
=======
        if (monto <= 0) return "Monto inválido";
        
        try (Connection con = DBConexion.getConnection()) {
            con.setAutoCommit(false);
            
            // 1. Actualizar saldo
            String sqlUpdate = "UPDATE cuenta SET dec_cuensaldo = dec_cuensaldo + ?, int_cuencontmov = int_cuencontmov + 1 WHERE chr_cuencodigo = ?";
            try (PreparedStatement ps = con.prepareStatement(sqlUpdate)) {
                ps.setDouble(1, monto);
                ps.setString(2, cuentaCodigo);
                int rows = ps.executeUpdate();
                if (rows == 0) {
                    con.rollback();
                    return "Cuenta no encontrada";
                }
            }
            
            // 2. Registrar movimiento (depósito)
            registrarMovimiento(con, cuentaCodigo, "003", monto, null, "9999"); // "003" = Código para depósito
            
            con.commit();
            return "Depósito exitoso";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

    @WebMethod
    public String retirar(String cuentaCodigo, double monto) {
        if (monto <= 0) return "Monto inválido";
        
        try (Connection con = DBConexion.getConnection()) {
            con.setAutoCommit(false);
            
            // 1. Verificar saldo y actualizar
            String sqlUpdate = "UPDATE cuenta SET dec_cuensaldo = dec_cuensaldo - ?, int_cuencontmov = int_cuencontmov + 1 WHERE chr_cuencodigo = ? AND dec_cuensaldo >= ?";
            try (PreparedStatement ps = con.prepareStatement(sqlUpdate)) {
                ps.setDouble(1, monto);
                ps.setString(2, cuentaCodigo);
                ps.setDouble(3, monto);
                int rows = ps.executeUpdate();
                if (rows == 0) {
                    con.rollback();
                    return "Saldo insuficiente o cuenta no encontrada";
                }
            }
            
            // 2. Registrar movimiento (retiro)
            registrarMovimiento(con, cuentaCodigo, "004", monto, null, "9999"); // "004" = Código para retiro
            
            con.commit();
            return "Retiro exitoso";
>>>>>>> f314e0f09a32b7739f700b8e03fe9c5690fb3ec1
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

    @WebMethod
<<<<<<< HEAD
    public String retirar(String cuentaCodigo, double monto) {
        if (monto <= 0) {
            return "Monto inválido";
        }
        String sql = "UPDATE cuenta SET dec_cuensaldo = dec_cuensaldo - ? WHERE chr_cuencodigo = ? AND dec_cuensaldo >= ?";
        try (Connection con = DBConexion.getConnection()) {
            con.setAutoCommit(false);
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setDouble(1, monto);
                ps.setString(2, cuentaCodigo);
                ps.setDouble(3, monto);
                int rows = ps.executeUpdate();
                if (rows > 0) {
                    movimientoService.registrarMovimiento(con, cuentaCodigo, "004", monto, "0001", null); // tipo 004 = RETIRO
                    con.commit();
                    return "Retiro exitoso";
                } else {
                    con.rollback();
                    return "Saldo insuficiente o cuenta no encontrada";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

    @WebMethod
    public String transferir(String origen, String destino, double monto) {
        if (monto <= 0) {
            return "Monto inválido";
        }
=======
    public String transferir(String origen, String destino, double monto) {
        if (monto <= 0) return "Monto inválido";
>>>>>>> f314e0f09a32b7739f700b8e03fe9c5690fb3ec1

        try (Connection con = DBConexion.getConnection()) {
            con.setAutoCommit(false);

<<<<<<< HEAD
            // Retirar de origen
            PreparedStatement ps1 = con.prepareStatement("UPDATE cuenta SET dec_cuensaldo = dec_cuensaldo - ? WHERE chr_cuencodigo = ? AND dec_cuensaldo >= ?");
=======
            // 1. Retirar de origen
            PreparedStatement ps1 = con.prepareStatement(
                "UPDATE cuenta SET dec_cuensaldo = dec_cuensaldo - ?, int_cuencontmov = int_cuencontmov + 1 " +
                "WHERE chr_cuencodigo = ? AND dec_cuensaldo >= ?");
>>>>>>> f314e0f09a32b7739f700b8e03fe9c5690fb3ec1
            ps1.setDouble(1, monto);
            ps1.setString(2, origen);
            ps1.setDouble(3, monto);
            int row1 = ps1.executeUpdate();

<<<<<<< HEAD
            // Depositar a destino
            PreparedStatement ps2 = con.prepareStatement("UPDATE cuenta SET dec_cuensaldo = dec_cuensaldo + ? WHERE chr_cuencodigo = ?");
=======
            // 2. Depositar a destino
            PreparedStatement ps2 = con.prepareStatement(
                "UPDATE cuenta SET dec_cuensaldo = dec_cuensaldo + ?, int_cuencontmov = int_cuencontmov + 1 " +
                "WHERE chr_cuencodigo = ?");
>>>>>>> f314e0f09a32b7739f700b8e03fe9c5690fb3ec1
            ps2.setDouble(1, monto);
            ps2.setString(2, destino);
            int row2 = ps2.executeUpdate();

            if (row1 > 0 && row2 > 0) {
<<<<<<< HEAD
                movimientoService.registrarMovimiento(con, origen, "004", monto, "0001", destino); // tipo 004 = RETIRO
                movimientoService.registrarMovimiento(con, destino, "003", monto, "0001", origen); // tipo 003 = DEPÓSITO    
=======
                // 3. Registrar movimientos
                registrarMovimiento(con, origen, "009", monto, destino, "9999"); // "009" = Transferencia (salida)
                registrarMovimiento(con, destino, "008", monto, origen, "9999"); // "008" = Transferencia (entrada)
                
>>>>>>> f314e0f09a32b7739f700b8e03fe9c5690fb3ec1
                con.commit();
                return "Transferencia exitosa";
            } else {
                con.rollback();
                return "Error en transferencia. Verifica saldos y cuentas.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

    @WebMethod
    public double verSaldo(String cuentaCodigo) {
        String sql = "SELECT dec_cuensaldo FROM cuenta WHERE chr_cuencodigo = ?";
<<<<<<< HEAD
        try (Connection con = DBConexion.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
=======
        try (Connection con = DBConexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
>>>>>>> f314e0f09a32b7739f700b8e03fe9c5690fb3ec1
            ps.setString(1, cuentaCodigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble("dec_cuensaldo");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @WebMethod
    public String probarConexion() {
        try (Connection con = DBConexion.getConnection()) {
            return con != null && !con.isClosed() ? "Conexión a BD exitosa." : "Conexión fallida.";
        } catch (SQLException e) {
            return "Error: " + e.getMessage();
        }
    }
<<<<<<< HEAD
}
=======
    
    // Métodos auxiliares para registrar movimientos
    
    private void registrarMovimiento(String cuentaCodigo, String tipoMovimiento, double importe, 
                                   String cuentaReferencia, String empleadoCodigo) throws SQLException {
        try (Connection con = DBConexion.getConnection()) {
            registrarMovimiento(con, cuentaCodigo, tipoMovimiento, importe, cuentaReferencia, empleadoCodigo);
        }
    }
    
    private void registrarMovimiento(Connection con, String cuentaCodigo, String tipoMovimiento, 
                                   double importe, String cuentaReferencia, String empleadoCodigo) throws SQLException {
        // Obtener el próximo número de movimiento
        int nextNum = obtenerProximoNumeroMovimiento(con, cuentaCodigo);
        
        String sql = "INSERT INTO movimiento(chr_cuencodigo, int_movinumero, dtt_movifecha, chr_emplcodigo, chr_tipocodigo, dec_moviimporte, chr_cuenreferencia) " +
                     "VALUES (?, ?, CURRENT_DATE(), ?, ?, ?, ?)";
        
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cuentaCodigo);
            ps.setInt(2, nextNum);
            ps.setString(3, empleadoCodigo);
            ps.setString(4, tipoMovimiento);
            ps.setDouble(5, importe);
            ps.setString(6, cuentaReferencia);
            ps.executeUpdate();
        }
    }
    
    private int obtenerProximoNumeroMovimiento(Connection con, String cuentaCodigo) throws SQLException {
        String sql = "SELECT IFNULL(MAX(int_movinumero), 0) + 1 FROM movimiento WHERE chr_cuencodigo = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cuentaCodigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 1; // Si no hay movimientos aún
        }
    }
}
>>>>>>> f314e0f09a32b7739f700b8e03fe9c5690fb3ec1
