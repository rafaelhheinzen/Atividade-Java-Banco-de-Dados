import java.sql.Connection;

import produto.ConectaDB;



public class Main {

    public static void main(String[] args) {
        Connection conn = ConectaDB.getConnection();
        Menu menu = new Menu();

        if (conn != null) {
            System.out.println("Aplicação conectada ao MySQL.");
        }else{
            System.out.println("Erro ao conectar!");
        }


        menu.repeat();

    }

}