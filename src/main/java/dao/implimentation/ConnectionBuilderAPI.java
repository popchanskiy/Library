package dao.implimentation;

import dao.DBConfigLoader;
import dao.contracts.ConnectionBuilder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionBuilderAPI implements ConnectionBuilder {
    private String driverName;
    private String url;
    private String login;
    private String password;
    private static ConnectionBuilderAPI instance;

    private ConnectionBuilderAPI() {

    }

    public static synchronized ConnectionBuilderAPI getInstance(){
        if(instance==null){
            instance=new ConnectionBuilderAPI();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        try {
            Class.forName(DBConfigLoader.getProperty("db.driver.class"));
            //Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        this.url = DBConfigLoader.getProperty("db.url");
       this.login = DBConfigLoader.getProperty("db.login");
       this.password = DBConfigLoader.getProperty("db.password");
      /*  this.url ="jdbc:postgresql://localhost:5432/library";
       this.login = "postgres";
       this.password="admin";*/

        return DriverManager.getConnection(url, login, password);
    }
}
