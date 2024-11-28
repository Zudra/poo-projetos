package com.fag.infra.postgres;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresConnection {

    private static PostgresConnection instance;

    private PostgresConnection() {
        System.out.println("Realizando conexão banco PG/SUPABASE🔌");

        String url = "jdbc:postgresql://URL_SEU_BANCO_SUPA:PORTA/BASE_DADOS";
        String username = "SEU_USERNAME_SUPA";
        String password = "SUA_SENHA_SHOW";

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, username, password);
            
            System.out.println("Deu boa! Banco conectado!🗂");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static synchronized PostgresConnection getInstance() {
        if (instance == null) {
            instance = new PostgresConnection();
        }

        return instance;
    }

}