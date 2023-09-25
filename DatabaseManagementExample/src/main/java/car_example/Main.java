package car_example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/test";
        String kullaniciAdi = "postgres";
        String parola = "admin";
        List<Cars> carsList=null;
        try ( Connection conn = DriverManager.getConnection(jdbcUrl, kullaniciAdi, parola)){
            // Veritabanına bağlantı oluşturma

            // INSERT işlemi
            String insertSQL = "INSERT INTO cars (brand, model,year) VALUES (?, ?, ?)";
            PreparedStatement insertStatement = conn.prepareStatement(insertSQL);
            insertStatement.setString(1, "Porsche");
            insertStatement.setString(2, "taycan");
            insertStatement.setInt(3, 2023);
            int affectedRows = insertStatement.executeUpdate();
            System.out.println("INSERT işlemi sonucunda etkilenen satır sayısı: " + affectedRows);

            // SELECT işlemi
            String selectSQL = "SELECT * FROM cars";
            PreparedStatement selectStatement = conn.prepareStatement(selectSQL);
            ResultSet resultSet = selectStatement.executeQuery();
            carsList=new ArrayList<>();
            while (resultSet.next()) {

                // Sonuçları işleme
                String brand = resultSet.getString("brand");
                String model = resultSet.getString("model");
                int year = resultSet.getInt("year");
                Cars cars=new Cars(brand,model,year);
                carsList.add(cars);
                System.out.println("brand: " + brand + ", year: " + year);
            }

            // Bağlantıyı kapatma
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(carsList);
    }
}