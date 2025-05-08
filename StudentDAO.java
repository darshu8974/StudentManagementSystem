import java.sql.*;
import java.util.*;

public class StudentDAO {

    public void addStudent(Student s) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String sql = "INSERT INTO students (name, age, department) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, s.name);
        ps.setInt(2, s.age);
        ps.setString(3, s.department);
        ps.executeUpdate();
        conn.close();
    }

    public List<Student> getAllStudents() throws SQLException {
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT * FROM students";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        List<Student> students = new ArrayList<>();
        while (rs.next()) {
            students.add(new Student(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("age"),
                rs.getString("department")
            ));
        }

        conn.close();
        return students;
    }

    public Student getStudentById(int id) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT * FROM students WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            Student s = new Student(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("age"),
                rs.getString("department")
            );
            conn.close();
            return s;
        }

        conn.close();
        return null;
    }

    public void updateStudent(Student s) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String sql = "UPDATE students SET name = ?, age = ?, department = ? WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, s.name);
        ps.setInt(2, s.age);
        ps.setString(3, s.department);
        ps.setInt(4, s.id);
        ps.executeUpdate();
        conn.close();
    }

    public void deleteStudent(int id) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String sql = "DELETE FROM students WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
        conn.close();
    }
}
