/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect;
import java.sql.*;
/**
 *
 * @author Mirado
 */
public class Connect {
    public Connect(){
    }
    public Connection getConnectionPsql()throws Exception{
        Connection connection;
        //étape 1: charger la classe de driver
        Class.forName("org.postgresql.Driver");
        //étape 2: créer l'objet de connexion
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/tests5s3","postgres","1776");
      
        return connection;
    } 
}
