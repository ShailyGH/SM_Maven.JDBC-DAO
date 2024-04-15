package daos;

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
            ResultSet rs = stmt.executeQuery("SELECT * FROM STUDENTS_DB.students WHERE id = " + id + ";");

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
            myStudent.setLast_name(rs.getNString("last_name"));
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
            ResultSet rs = stmt.executeQuery("SELECT * FROM STUDENTS_DB.students;");
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
            Statement stmt = myConnection.createStatement();
            int i = stmt.executeUpdate("UPDATE STUDENTS_DB.students SET first_name='" + object.getFirst_name() + "', last_name='" + object.getLast_name() + "', age='" + object.getAge() + "', height='" + object.getHeight() + "', weight='" + object.getWeight() + "' where id='" + object.getId() + "';");

            if (i == 1)
            {
                return findById(object.getId());
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
            Statement stmt = myConnection.createStatement();
            String sql = String.format("INSERT INTO STUDENTS_DB.students (id, first_name, last_name, age, height, weight) VALUES (%d, '%s','%s',%d, %d, %d);",object.getId(), object.getFirst_name(),object.getLast_name(),object.getAge(),object.getHeight(), object.getWeight());

            stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

            ResultSet rs = stmt.getGeneratedKeys();

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
            stmt.executeUpdate("DELETE FROM STUDENTS_DB.students WHERE id = '" + id + "';");
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
}
