/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HOME
 */
public class DBConnectionFactory {
    private Connection connection;
    private static DBConnectionFactory instance;
    
    private DBConnectionFactory() {}
    
    public static DBConnectionFactory getInstance() {
        if(instance == null){
            instance = new DBConnectionFactory();
        }
        return instance;
    }
    
    public Connection getConnection() throws SQLException {
        try {
            if(connection == null || connection.isClosed()){
                Properties properties = new Properties();
                properties.load(new FileInputStream("dbconfig.properties"));
                String url = properties.getProperty("url");
                String username = properties.getProperty("username");
                String password = properties.getProperty("password");
                connection = DriverManager.getConnection(url, username, password);
                connection.setAutoCommit(false);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DBConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DBConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }
}
