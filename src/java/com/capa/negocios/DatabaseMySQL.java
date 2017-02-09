package com.capa.negocios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class DatabaseMySQL {

    private Connection conexion;

    public DatabaseMySQL() {
        String url;
        String server = "localhost";
        String db = "db_inventario_hee";
        String user = "root";
        String pass = "root";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            url = "jdbc:mysql://" + server + "/" + db + "?connectTimeout=7000&socketTimeout=7000";
            DriverManager.setLoginTimeout(1);
            conexion = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            System.out.println("Error de conexión!. Quizá user/password son inválidos" + e.getMessage());
        }
    }

    public void close() {
        try {
            conexion.close();
        } catch (SQLException error) {
            String message = null;
            message = "<html><p><b>Error de Mysql: </b> " + error.getMessage() + "</p> " + "<p><b>Código de Error: </b>"
                    + error.getErrorCode() + " </p></html>";
            JOptionPane.showMessageDialog(new JFrame(), message);
        }
    }

    public void insertar(String insert) {
        Statement comando;
        try {
            comando = conexion.createStatement();
            comando.executeUpdate(insert);
        } catch (SQLException error) {
            String message = null;
            message = "<html><p><b>Error de Mysql: </b> " + error.getMessage() + "</p> " + "<p><b>Código de Error: </b>"
                    + error.getErrorCode() + " </p></html>";
            JOptionPane.showMessageDialog(new JFrame(), message);
        }
    }
}
