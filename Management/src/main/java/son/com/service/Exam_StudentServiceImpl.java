package son.com.service;
import son.com.dao.Exam_StudentDao;
import son.com.model.Exam_Student;
public class Exam_StudentServiceImpl implements Exam_StudentService{
	private Exam_StudentDao examStudentDAO;

    public Exam_StudentServiceImpl(Exam_StudentDao examStudentDAO) {
        this.examStudentDAO = examStudentDAO;
    }

    @Override
    public void saveExamAttempt(Exam_Student examStudent) {
        examStudentDAO.saveExamAttempt(examStudent);
    }
}
