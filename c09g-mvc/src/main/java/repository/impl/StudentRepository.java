package repository.impl;

import bean.Student;
import repository.IStudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class StudentRepository implements IStudentRepository {

    static private Map<Integer, Student> studentMap;

    static {
        studentMap = new TreeMap<>();
        studentMap.put(324, new Student(324, "Nguyen Van A",
                "2000-11-29", 1, 7.0));
        studentMap.put(653, new Student(653, "Le Van B",
                "2000-07-08", 1, 2.0));
        studentMap.put(123, new Student(123, "Tran Thi C",
                "1999-12-08", 0, 9.0));
    }

    @Override
    public List<Student> findAll() {
        return new ArrayList<>(studentMap.values());
    }

    @Override
    public Student findById(Integer id) {
        return null;
    }

    @Override
    public void save(Student student) {
        // Dang ky thong tin vao DB.
    }
}
