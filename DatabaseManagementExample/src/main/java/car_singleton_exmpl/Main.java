package car_singleton_exmpl;

import org.example.Car;
import org.example.CarDbProcess;
import org.example.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DbConnection instanceDb = DbConnection.getInstance();
        CarDbProcess instanceCarProcess=CarDbProcess.getInstance();

        Car insertCar = new Car("Porsche", "taycan", 2302);
        List<Car> carList = new ArrayList<>();
        try (Connection conn = instanceDb.getConnection()) { //try-with-resource // no-needed conn.close in finally
            instanceCarProcess.insertCar(conn, insertCar);
            carList.addAll(instanceCarProcess.selectAllCars(conn));
            //     carDbProcess.deleteCarByModel(conn, "taycan");
            instanceCarProcess.updateCarModelByYear(conn, "java", 2053);
            // Bağlantıyı kapatma
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //System.out.println(carList); for-each
    }
}