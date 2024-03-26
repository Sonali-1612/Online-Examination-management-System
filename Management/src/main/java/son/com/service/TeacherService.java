package son.com.service;

import son.com.model.Teacher;

public interface TeacherService {
	public void insertTeacher(Teacher teacher);
	public void updateTeacher(Teacher teacher);
	public void deleteTeacher(Teacher teacher);
	public void displayAllTeacher();
}
