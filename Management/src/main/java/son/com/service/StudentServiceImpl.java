package son.com.service;
import son.com.model.Student;
import son.com.dao.StudentDao;

public class StudentServiceImpl implements StudentService {
	private StudentDao studentDao;

    // Constructor to initialize the StudentDao
    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }
    
    @Override
    public Student getStudentById(int studentID) {
        return studentDao.getStudentById(studentID);
    }

    @Override
    public void insertStudent(Student student) {
        studentDao.insertStudent(student);
    }

    @Override
    public void updateStudent(Student student) {
        studentDao.updateStudent(student);
    }

    @Override
    public void deleteStudent(Student student) {
        studentDao.deleteStudent(student);
    }

    @Override
    public void displayAllStudent() {
        studentDao.displayAllStudent();
    }
    @Override
    public Student displayStudentDetails(int studentId) {
    	return studentDao.displayStudentDetails(studentId);
    }

}
