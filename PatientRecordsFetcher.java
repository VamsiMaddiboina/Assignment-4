import java.sql.*;

public class PatientRecordsFetcher {
    public static void main(String[] args) {
        try {
            String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
            String username = "your_username";
            String password = "your_password"; // Replace with your actual password

            try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
                 Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery("SELECT patient_id, patient_name, diagnosis, total_bill FROM patient_records")) {

                while (resultSet.next()) {
                    int patientId = resultSet.getInt("patient_id");
                    String patientName = resultSet.getString("patient_name");
                    String diagnosis = resultSet.getString("diagnosis");
                    double totalBill = resultSet.getDouble("total_bill");

                    System.out.println("Patient ID: " + patientId);
                    System.out.println("Name: " + patientName);
                    System.out.println("Diagnosis: " + diagnosis);
                    System.out.println("Total Bill: " + totalBill);
                    System.out.println("---------------------");
                }
            } catch (SQLException e) {
                System.err.println("Error: " + e.getMessage());
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Error: JDBC driver not found");
        }
    }
}