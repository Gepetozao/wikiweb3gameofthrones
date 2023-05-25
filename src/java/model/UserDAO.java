package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private static Connection conn;
        
        public UserDAO() throws ClassNotFoundException, SQLException {
            conn = MyConnection.getConnection();
        }
        
        //INSERT
        public void insertUser(User u) throws SQLException{
           //Criando a query genérica
            String sql = "INSERT INTO usuarios(nome, senha)"
                                        + "VALUES (?, ?)";
            
            //Instanciando o objeto de conexão informando a query
            PreparedStatement prep = conn.prepareStatement(sql);
            
            //Informando os parâmetros enviados para a query
            prep.setString(1, u.getUserName());
            prep.setString(2, u.getUserPass());
            
            prep.execute(); //Lançando o SQL pronto na base de dados
            prep.close();
        }//Fim do método insertUser
        
        
        //SELECT BY ID
        public User listOneUser(String nome) throws SQLException {
             String query = "SELECT * FROM usuarios WHERE nome = ?";
             
             PreparedStatement prep = conn.prepareStatement(query);
             prep.setString(1, nome);
             
             ResultSet result = prep.executeQuery();
             
             User user = new User();
             
             if(result.next()){
                user.setUserId(result.getInt("id_usuario"));
                user.setUserName(result.getString("nome"));
                user.setUserPass(result.getString("senha"));
             }             
             return user;
        }
}
