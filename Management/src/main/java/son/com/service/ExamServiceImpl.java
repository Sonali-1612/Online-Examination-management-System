package son.com.service;

import son.com.dao.ExamDao;

public class ExamServiceImpl implements ExamService{
	private ExamDao examDao;

    public ExamServiceImpl(ExamDao examDao) {
        this.examDao = examDao;
    }

    @Override
    public void insertExam(int teacherID) {
        examDao.insertExam(teacherID);
    }

    @Override
    public void updateExam(int teacherID) {
        examDao.updateExam(teacherID);
    }

    @Override
    public void deleteExam(int teacherID) {
        examDao.deleteExam(teacherID);
    }

}
