package br.com.senac.fabio.jdbc.connection.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtils {

    //Armazena o objeto de conexão
    // ESSA CLASSE CONECTA A CONEXAO PARA RODAR OS COMANDOS
    private static Connection connection = null;

    //Obtém uma conexão com o banco de dados
    //Essa classe é para criar conexão com o banco
    //um tratamento de erro
    public static Connection getConnection() throws SQLException {
        //Só tenta abrir uma conexão se esta já não
        //existir ou estiver fechada
        // se nao tem conexao ou a conexao esta fechada, abre outra.
        if (connection == null || connection.isClosed()) {

            //Declaração de endereço de conexão com o banco de dados
            //essa é a url do banco, as vezes muda aonde esta depois da //
            String dbUrl = "jdbc:derby://localhost:1527/jdbc_teste";

            //Propriedades para armazenamento do usuário e da senha do banco
            //mapa de parametro, properties. tem que ser a mesam coisa que deu lá no banco
            // put, é colocar uma chave nova
            Properties properties = new Properties();
            properties.put("user", "utest");
            properties.put("password", "test");

            //Realiza a conexão com o banco
            connection = DriverManager.getConnection(dbUrl, properties);
        }

        //Retorna a conexão a quem chamou
        return connection;
    }
}
