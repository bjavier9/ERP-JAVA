package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class conexion {

    private String database = "proyecto.accdb";
    private String username = "";
    private String password = "";
    private String url = "jdbc:ucanaccess://" + System.getProperty("user.dir").replace("\\", "/") + "/" + database;

    public Connection conn = null;

    public conexion() {
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Connection getConnection() {
        return conn;
    }

    public void desconectar() throws SQLException {
        conn.close();
    }
}