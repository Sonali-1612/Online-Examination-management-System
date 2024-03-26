package son.com.service;

public interface QuestionService {

	void addQuestionToExam(int examID);

	void updateQuestion(int examID);

	void deleteQuestion(int examID);

	void viewQuestionsInExam(int examID);

}
