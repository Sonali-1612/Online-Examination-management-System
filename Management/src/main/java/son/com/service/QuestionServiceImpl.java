package son.com.service;

import son.com.dao.QuestionDao;

public class QuestionServiceImpl implements QuestionService{
	private QuestionDao questionDao;

    public QuestionServiceImpl(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    @Override
    public void addQuestionToExam(int examID) {
        questionDao.addQuestionToExam(examID);
    }

    @Override
    public void updateQuestion(int examID) {
        questionDao.updateQuestion(examID);
    }

    @Override
    public void deleteQuestion(int examID) {
        questionDao.deleteQuestion(examID);
    }

    @Override
    public void viewQuestionsInExam(int examID) {
        questionDao.viewQuestionsInExam(examID);
    }
}
