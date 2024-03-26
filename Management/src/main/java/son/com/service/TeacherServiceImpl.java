package son.com.service;
import son.com.model.Teacher;
import son.com.dao.TeacherDao;

public class TeacherServiceImpl implements TeacherService {
	private TeacherDao teacherDao;

    // Constructor to initialize the StudentDao
    public TeacherServiceImpl(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }
    public boolean authenticateTeacher(int teacherId, String password) {
        Teacher teacher = teacherDao.getTeacherById(teacherId);
        return teacher != null && teacher.getPassword().equals(password);
    }

    @Override
    public void insertTeacher(Teacher teacher) {
    	teacherDao.insertTeacher(teacher);
    }

    @Override
    public void updateTeacher(Teacher teacher) {
    	teacherDao.updateTeacher(teacher);
    }

    @Override
    public void deleteTeacher(Teacher teacher) {
    	teacherDao.deleteTeacher(teacher);
    }

    @Override
    public void displayAllTeacher() {
    	teacherDao.displayAllTeacher();
    }

}
