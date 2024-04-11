package daos;

import models.Student;

public class StudentsDTO implements DTO<Student>{

    @Override
    public int getId(Student object) {
        return 0;
    }
}
