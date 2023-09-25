package car_singleton_example;

import org.example.CarDbProcess;
import org.example.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CarDbProcess carDbProcess = new CarDbProcess();
        DbConnection instance = DbConnection.getInstance();

        Car insertCar = new Car("Porsche", "taycan", 2302);
        List<Car> carList = new ArrayList<>();
        try (Connection conn = instance.getConnection()) { //try-with-resource // no-needed conn.close in finally
            carDbProcess.insertCar(conn, insertCar);
            carList.addAll(carDbProcess.selectAllCars(conn));
            //     carDbProcess.deleteCarByModel(conn, "taycan");
            carDbProcess.updateCarModelByYear(conn, "java", 2053);
            // Bağlantıyı kapatma
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //System.out.println(carList); for-each
    }
}