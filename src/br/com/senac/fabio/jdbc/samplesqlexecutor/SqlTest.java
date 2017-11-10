package br.com.senac.fabio.jdbc.samplesqlexecutor;

//Executor de códigos SQL/banco de dados
import br.com.senac.fabio.jdbc.connection.utils.ConnectionUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlTest {

    //Elemento para armazenamento da conexão
    //atributo do tipo conection e varial nova
    private static Connection con = null;

    // é tipo os comando sql
    private static void insert() throws SQLException {
        // cria uma declaração do comando SQL
        //todo comando SQL vai ser string
        String sql = "INSERT INTO cliente VALUES (?, ?, ?)";

        //Cria um onjeto de statement para a execução dos comandos SQL
        // Objeto do tipo Statement, passa importando 
        PreparedStatement pst = con.prepareStatement(sql);

        //Configura os parametros, vai colocar os valores do ? para o inteiro e string que nem do banco
        //sempre começa com 1
        pst.setInt(1, 2);
        pst.setString(2, "Marcelo");
        pst.setInt(3, 20);
        //ex:
        //INSERT INTO cliente
        //VALUES(0, 'VALENTINA' , 19)

        // executa o comando SQL de cima: comando execute
        //delete insert 
        pst.execute();
    }

    private static void listAll() throws SQLException {
        // primeira coisa tem que criar um comando SQL
        String sql = "SELECT * FROM cliente";

        // cria um objeto de statemente para a execução
        PreparedStatement pst = con.prepareStatement(sql);

        // executa a pesquisa SQL e armazena o resultado 
        // esse result precisa dar o next sempre
        ResultSet rs = pst.executeQuery();

        //obtem os dados de cada linha da tabela
        while (rs.next()) {
            System.out.println("Nome:" + rs.getString("nome")
                    + " | Idade:" + rs.getInt("idade"));

        }
    }

    private static void update() throws SQLException {
        String sql = "UPDATE cliente SET nome= ?, idade = ? WHERE id = ?";

        PreparedStatement pst = con.prepareStatement(sql);

        pst.setString(1, "Vitória");
        pst.setInt(2, 20);
        pst.setInt(3, 0);
        
        pst.execute();
    }
    private static void delete()throws SQLException {
        String sql = "DELETE FROM cliente WHERE id = ?";

        PreparedStatement pst = con.prepareStatement(sql);

        
        pst.setInt(1, 1);
        
        pst.execute();
    }

    //Método de execução principal
    public static void main(String[] args) {
        //Tratamento de erros de conexão com o banco
        try {
            //Tenta obter uma conexão com o banco
            con = ConnectionUtils.getConnection();
            System.out.println("Conectado!");
            //insert();
           listAll();
           // update();
            delete();
            
            //SEU COMANDO SQL AQUI!   
        } catch (Exception e) {
            //Imprime erros de conexão
            e.printStackTrace();
        } finally {
            // dependente do que acontecer, sempre vai executar
            //Trata erros do fechamento de conexão com o banco de dados
            try {
                //Tenta fechar a conexão com o banco
                // e pode ter erro logo em seguida coloca um catch para isso
                con.close();
            } catch (Exception e) {
                //Imprime erros de fechamento de conexão
                e.printStackTrace();
            }
        }
    }
}
