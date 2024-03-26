package son.com.dao;
//import son.com.model.Exam;
public interface ExamDao {
    void insertExam(int teacherID);
	void updateExam(int teacherID);
	void deleteExam(int teacherID);
}
