package students;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        //Enter new student
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Student number: ");
        int studentNo = scanner.nextInt();
        System.out.print("Enter Student name: ");
        String studentNam = scanner.next();
        System.out.print("Enter Student score: ");
        double studentScore = scanner.nextDouble();

        //Enter a student number
        System.out.print("Enter a student number: ");
        int studentNumberEnter = scanner.nextInt();


        try (Connection conn = createConnection()) {
            insertStudent(conn, studentNo, studentNam, studentScore);
            selectStudent(conn, studentNo, studentNam, studentScore, studentNumberEnter);

            //Bağlantıyı kapatma
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private static Connection createConnection() throws SQLException {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/test";
        String kullaniciAdi = "postgres";
        String parola = "admin";
        return DriverManager.getConnection(jdbcUrl,kullaniciAdi,parola);
    }

    private static void insertStudent(Connection conn, int studentNo, String studentNam, double studentScore) throws SQLException {
        Students inserStudents = new Students(studentNo, studentNam, studentScore);
        String insertSQL = "INSERT INTO students (student_id,student_name,score) VALUES (?,?,?)";
        PreparedStatement insertStatement = conn.prepareStatement(insertSQL);
        insertStatement.setInt(1, inserStudents.getStudent_id());
        insertStatement.setString(2, inserStudents.getStudent_name());
        insertStatement.setDouble(3, inserStudents.getScore());
        int affectedRows = insertStatement.executeUpdate();
        System.out.println("INSERT işlemi sonucunda etkilenen satır sayısı: " + affectedRows);

    }

    private static void selectStudent(Connection conn, int studentNo, String studentNam, double studentScore,int studentNumberEnter) throws SQLException {
        List<Students> studentsList = new ArrayList<>();
        //SELECT işlemi Single Student
        String singleSelectSQL = "SELECT * FROM students WHERE student_id=?";
        PreparedStatement singleSelectStatement = conn.prepareStatement(singleSelectSQL);
        singleSelectStatement.setInt(1, studentNumberEnter);
        ResultSet singleResultSet = singleSelectStatement.executeQuery();

        while (singleResultSet.next()) {
            int singleStudentId = singleResultSet.getInt("student_id");
            String singleStudentName = singleResultSet.getString("student_name");
            double singleScore = singleResultSet.getDouble("score");
            Students students = new Students(singleStudentId, singleStudentName, singleScore);
            studentsList.add(students);
            System.out.println("student no: " + singleStudentId + ", student name: " + singleStudentName + ", score: " + singleScore);
        }

        //SELECT işlemi All student
        System.out.println("All Student List");
        String selectSQL = "SELECT * FROM students";
        PreparedStatement selectStatement = conn.prepareStatement(selectSQL);
        ResultSet resultSet = selectStatement.executeQuery();
        while (resultSet.next()) {
            int studentId = resultSet.getInt("student_id");
            String studentName = resultSet.getString("student_name");
            double score = resultSet.getDouble("score");
            Students students = new Students(studentId, studentName, score);
            studentsList.add(students);
            System.out.println("student no: " + studentId + ", student name: " + studentName + ", score: " + score);
        }

    }
}
