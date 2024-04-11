package daos;

import models.Student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentsDAO implements DAO<Student>{

    private Connection myConnection = DBConnection.getConnection();

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
    public Student update(Student object) {


        return null;
    }

    @Override
    public Student create(Student object) {
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
