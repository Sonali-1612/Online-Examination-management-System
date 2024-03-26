package son.com.dao;

import java.util.List;


import son.com.model.Teacher;

public interface TeacherDao {
	void insertTeacher(Teacher teacher);
    void updateTeacher(Teacher teacher);
    void deleteTeacher(Teacher teacher);
    Teacher getTeacherById(int teacherID);
    List<Teacher> displayAllTeacher();
}
