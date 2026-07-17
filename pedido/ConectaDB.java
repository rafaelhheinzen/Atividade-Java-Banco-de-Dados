package pedido;


import java.sql.Connection;
import java.sql.DriverManager;



public class ConectaDB {

    private static final String URL = "jdbc:mysql://localhost:3306/auladb";
    private static final String USER = "root";
    private static final String PASSWORD = "@1@senac2021";

    public static Connection getConnection() {

        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexão com o banco realizada com sucesso!");
            return conn;

        } catch (Exception e) {
            System.out.println("Erro ao conectar com o banco de dados!");
            System.out.println("Motivo: " + e.getMessage());
            return null;
        }
    }
}