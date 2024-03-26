package son.com.utility;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import son.com.model.Exam;
import son.com.model.Exam_Student;
import son.com.model.Questions;
import son.com.model.Student;
import son.com.model.Teacher;

public class HibernetUtil {
    
	private final static SessionFactory sessionFactory=buildSessionFactory();
	private static SessionFactory buildSessionFactory()
	{

		try {
			return new Configuration().configure("exam.cfg.xml")
					.addAnnotatedClass(Student.class)
					.addAnnotatedClass(Teacher.class)
					.addAnnotatedClass(Exam.class)
					.addAnnotatedClass(Questions.class)
					.addAnnotatedClass(Exam_Student.class)
					.buildSessionFactory();
			
		}catch (Throwable e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static Session getSession()
	{
	  return getSessionFactory().openSession(); //session opened
	}
}
