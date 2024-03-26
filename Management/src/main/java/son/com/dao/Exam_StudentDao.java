package son.com.dao;

import java.util.List;

import org.hibernate.Session;

import son.com.model.Exam_Student;
import son.com.model.Questions;

public interface Exam_StudentDao {

	void saveExamAttempt(Exam_Student examStudent);

	List<Questions> getQuestionsByExamID(int examID, Session session);

}
