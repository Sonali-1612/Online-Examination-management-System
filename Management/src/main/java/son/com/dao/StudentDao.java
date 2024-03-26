package son.com.dao;

import java.util.List;

import son.com.model.Student;

public interface StudentDao {
	void insertStudent(Student student);
    void updateStudent(Student student);
    void deleteStudent(Student student);
    Student getStudentById(int studentID);
    List<Student> displayAllStudent();
	Student displayStudentDetails(int studentId);
}
