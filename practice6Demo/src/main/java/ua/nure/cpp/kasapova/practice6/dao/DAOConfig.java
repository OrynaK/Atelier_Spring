package ua.nure.cpp.kasapova.practice6.dao;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@ConfigurationProperties(prefix = "database")
public class DAOConfig {
    private String type;
    private static String url;
    private static String user;
    private static String password;

    public DAOConfig() {
    }

    public DAOConfig(String type) {
        this.type = type;
    }

    public DAOConfig(String type, String url, String user, String password) {
        this.type = type;
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public static Connection getConnection() throws SQLException {
        return getConnection(false);
    }

    public static Connection getConnection(boolean autoCommit) throws SQLException {
        Connection con = DriverManager.getConnection(url, user, password);
        if (autoCommit) {
            con.setAutoCommit(autoCommit);
            con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        }
        return con;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "DAOConfig{" +
                "type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
