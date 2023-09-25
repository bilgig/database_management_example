package car_singleton_example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static DbConnection instance;

    private Connection connection;
    private String jdbcUrl = "jdbc:postgresql://localhost:5432/test";
    private String kullaniciAdi = "postgres";
    private String parola = "admin";

    static {
        try {
            instance = new DbConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private DbConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(jdbcUrl, kullaniciAdi, parola);
            System.out.println("Database Connection successful");
        } catch (ClassNotFoundException ex) {
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
        }
    }

    public static DbConnection getInstance() {
        try {
            if (instance == null) {
                instance = new DbConnection();
            } else if (instance.getConnection().isClosed()) {
                instance = new DbConnection();
            }
            return instance;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}