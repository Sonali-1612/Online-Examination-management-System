package son.com.dao;

public interface QuestionDao {

	void addQuestionToExam(int examID);

	void updateQuestion(int examID);

	void deleteQuestion(int questionID);

	void viewQuestionsInExam(int examID);

}
