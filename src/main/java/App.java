import daos.StudentsDAO;
import models.Student;

import java.util.ArrayList;
import java.util.List;

public class App
{
    public static void main(String[] args)
    {
        StudentsDAO sDAO = new StudentsDAO();
        List<Student> students;

        System.out.println("************* Create new records **************");

        sDAO.create(new Student(1, "John", "Doe", 55, 65, 230 ));
        sDAO.create(new Student(2, "Jane", "Doe", 52, 62, 175 ));
        sDAO.create(new Student(3, "Jack", "Doe", 25, 68, 190 ));

        System.out.println("*** "+ sDAO.findAll().size() + " new records created ***");

        System.out.println("\n************* Print all records **************");

        students = new ArrayList<Student>(sDAO.findAll());
        for (Student s: students)
        {
            System.out.println(s.getId() + " " + s.getFirst_name() + " " + s.getLast_name() + " " + s.getAge() + " " + s.getHeight() + " " + s.getWeight());
        }

        System.out.println("\n************* Print record by ID **************");
        Student s = sDAO.findById(students.get(1).getId());
        System.out.println("*** Printing record # " + s.getId() + " ***");
        System.out.println(s.getId() + " " + s.getFirst_name() + " " + s.getLast_name() + " " + s.getAge() + " " + s.getHeight() + " " + s.getWeight());

        System.out.println("\n************* Update record **************");
        Student st = sDAO.findById(students.get(0).getId());
        System.out.println("*** Current info of record # " + st.getId() + " ***");
        System.out.println(st.getId() + " " + st.getFirst_name() + " " + st.getLast_name() + " " + st.getAge() + " " + st.getHeight() + " " + st.getWeight());

        // set new values of student index 2
        st.setFirst_name("Jake");
        st.setAge(30);
        st.setHeight(72);
        st.setWeight(210);
        sDAO.update(s);
        System.out.println("\n*** Updated info of record # " + st.getId() + " ***");
        System.out.println(st.getId() + " " + st.getFirst_name() + " " + st.getLast_name() + " " + st.getAge() + " " + st.getHeight() + " " + st.getWeight());

        System.out.println("\n************* Delete record **************");

        sDAO.create(new Student(5, "Philip", "Williams" , 59, 67, 210));
        System.out.println("*** Record # 5 created ***");

        students = new ArrayList<Student>(sDAO.findAll());
        for (Student stu: students)
        {
            if(stu.getId() == 5)
            {
                sDAO.delete(stu.getId());
                System.out.println("*** Record # " + stu.getId() + " deleted ***");

            }
        }
    }
}
