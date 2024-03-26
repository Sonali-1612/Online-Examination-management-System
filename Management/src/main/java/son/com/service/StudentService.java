package son.com.service;

import son.com.model.Student;

public interface StudentService {
	public void insertStudent(Student student);
	public void updateStudent(Student student);
	public void deleteStudent(Student student);
	public void displayAllStudent();
	Student getStudentById(int studentID);
	Student displayStudentDetails(int studentId);
}
