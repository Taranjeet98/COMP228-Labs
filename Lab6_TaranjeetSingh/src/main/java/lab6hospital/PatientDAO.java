package lab6hospital;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {
	String url = "jdbc:mysql://localhost:3306/hospital_db";
    String username = "root";
    String password = "Matharu@1998";

    private Connection connect() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL driver not found");
        }
        return DriverManager.getConnection(url, username, password);
    }

    public void insert(Patient p) throws SQLException {
        String qry = "insert into patients (name, age, gender, phone, diagnosis, admission_date) values (?, ?, ?, ?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement ps = conn.prepareStatement(qry)) {
            ps.setString(1, p.getName());
            ps.setInt(2, p.getAge());
            ps.setString(3, p.getGender());
            ps.setString(4, p.getPhone());
            ps.setString(5, p.getDiagnosis());
            ps.setDate(6, Date.valueOf(p.getAdmissionDate()));
            ps.executeUpdate();
        }
    }

    public List<Patient> findAll() throws SQLException {
        String qry = "select * from patients";
        List<Patient> list = new ArrayList<>();

        try (Connection conn = connect();
             PreparedStatement ps = conn.prepareStatement(qry);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(map(rs));
            }
        }
        return list;
    }

    public List<Patient> search(String keyword) throws SQLException {
        String qry = "select * from patients where name like ? or phone like ?";
        List<Patient> list = new ArrayList<>();

        try (Connection conn = connect();
             PreparedStatement ps = conn.prepareStatement(qry)) {

            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(map(rs));
                }
            }
        }
        return list;
    }

    public void update(Patient p) throws SQLException {
        String qry = "update patients set name = ?, age = ?, gender = ?, phone = ?, diagnosis = ?, admission_date = ? where patient_id = ?";

        try (Connection conn = connect();
             PreparedStatement ps = conn.prepareStatement(qry)) {
            ps.setString(1, p.getName());
            ps.setInt(2, p.getAge());
            ps.setString(3, p.getGender());
            ps.setString(4, p.getPhone());
            ps.setString(5, p.getDiagnosis());
            ps.setDate(6, Date.valueOf(p.getAdmissionDate()));
            ps.setInt(7, p.getPatientId());

            ps.executeUpdate();
        }
    }

    public void delete(int patientId) throws SQLException {
        String qry = "delete from patients where patient_id = ?";

        try (Connection conn = connect();
           PreparedStatement ps = conn.prepareStatement(qry)) {
            ps.setInt(1, patientId);
            ps.executeUpdate();
        }
    }

    private Patient map(ResultSet rs) throws SQLException {
        int id = rs.getInt("patient_id");
        String name = rs.getString("name");
        int age = rs.getInt("age");
        String gender = rs.getString("gender");
        String phone = rs.getString("phone");
        String diagnosis = rs.getString("diagnosis");
        Date admissionDate = rs.getDate("admission_date");

        return new Patient(id, name, age, gender, phone, diagnosis, admissionDate.toLocalDate());
    }
}