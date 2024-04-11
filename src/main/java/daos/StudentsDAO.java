package daos;

import com.mysql.cj.xdevapi.PreparableStatement;
import models.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentsDAO implements DAO<Student>{

    private final Connection myConnection = DBConnection.getConnection();

    @Override
    public Student findById(int id) {
        try
        {
            Statement stmt = myConnection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM students WHERE id = " + id + ";");

            if(rs.next())
            {
                return extractUserFromResultSet(rs);
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    private Student extractUserFromResultSet(ResultSet rs)
    {
        try
        {
            Student myStudent = new Student();

            myStudent.setId(rs.getInt("id"));
            myStudent.setFirst_name(rs.getNString("first_name"));
            myStudent.setLast_name(rs.getNString("first_name"));
            myStudent.setAge(rs.getInt("age"));
            myStudent.setHeight(rs.getInt("height"));
            myStudent.setWeight(rs.getInt("weight"));

            return myStudent;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Student> findAll() {
        try
        {
            Statement stmt = myConnection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM students;");
            ArrayList<Student> studentsList = new ArrayList<Student>();

            while(rs.next())
            {
                Student student = extractUserFromResultSet(rs);
                studentsList.add(student);
            }
            return studentsList;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Student update(Student object)
    {
        try {
            int id = object.getId();
            Statement stmt = myConnection.createStatement();
            int i = stmt.executeUpdate("UPDATE students SET first_name=" + object.getFirst_name() + ", last_name=" + object.getLast_name() + ", age=" + object.getAge() + ", height=" + object.getHeight() + ", weight=" + object.getWeight() + ";");

            if (i == 1)
            {
                return findById(id);
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Student create(Student object) {
        try {
            PreparedStatement ps = myConnection.prepareStatement("INSERT INTO students VALUES (NULL, ?, ?, ?, ?, ?)");
            ps.setString(1, object.getFirst_name());
            ps.setString(2, object.getLast_name());
            ps.setInt(3, object.getAge());
            ps.setInt(4, object.getHeight());
            ps.setInt(5, object.getWeight());

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next())
            {
                return findById(rs.getInt(1));
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(int id) {

        try {
            Statement stmt = myConnection.createStatement();
            stmt.executeQuery("DELETE FROM students WHERE id = " + id + ";");
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
}
