package service.impl;

import bean.Student;
import repository.IStudentRepository;
import repository.impl.StudentRepository;
import service.IStudentService;

import java.util.List;

public class StudentService implements IStudentService {

    // DI
    private IStudentRepository iStudentRepository = new StudentRepository();

    @Override
    public List<Student> findAll() {
        return this.iStudentRepository.findAll();
    }

    @Override
    public Student findById(Integer id) {
        return null;
    }

    @Override
    public void save(Student student) {
        // 1. Validate thong tin hoc vien: regex, if, else
        // => Neu du lieu hop le thi goi Repository => save()
        // => Neu khong thi tra loi ve cho Servlet
    }
}
