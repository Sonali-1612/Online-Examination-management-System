package son.com.dao;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import son.com.model.Student;
import son.com.model.Teacher;
import son.com.utility.HibernetUtil;

public class StudentDAOImpl implements StudentDao{
    
    private SessionFactory sessionFactory;

    public StudentDAOImpl() {
        this.sessionFactory = HibernetUtil.getSessionFactory();
    }
    @Override
    public void insertStudent(Student student) {
    	 Transaction transaction = null;
         try (Session session = sessionFactory.openSession()) {
             transaction = session.beginTransaction();

             Scanner scanner = new Scanner(System.in);
             System.out.println("Enter studentID:");
             int studentID = scanner.nextInt();
             scanner.nextLine(); // Consume newline left-over
             System.out.println("Enter name:");
             String name = scanner.nextLine();
             System.out.println("Enter email:");
             String email = scanner.nextLine();
             System.out.println("Enter password:");
             String password = scanner.nextLine();

             Student student1 = new Student(studentID, name, email, password);
             session.save(student1);

             transaction.commit();
             System.out.println("Student inserted successfully.");
         } catch (Exception e) {
             if (transaction != null) {
                 transaction.rollback();
             }
             e.printStackTrace();
         }
    }
    @Override
    public void updateStudent(Student student) {
    	Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter studentID to update:");
            int studentID = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over
            Student student1 = session.get(Student.class, studentID);
            if (student1 != null) {
                System.out.println("Enter updated name:");
                String name = scanner.nextLine();
                System.out.println("Enter updated email:");
                String email = scanner.nextLine();
                System.out.println("Enter updated password:");
                String password = scanner.nextLine();

                student1.setName(name);
                student1.setEmail(email);
                student1.setPassword(password);

                session.update(student1);
                transaction.commit();
                System.out.println("Student updated successfully.");
            } else {
                System.out.println("Student with ID " + studentID + " not found.");
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    @Override
    public void deleteStudent(Student student) {
    	Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter studentID to delete:");
            int studentID = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over
            Student student1 = session.get(Student.class, studentID);
            if (student1 != null) {
                session.delete(student1);
                transaction.commit();
                System.out.println("Student deleted successfully.");
            } else {
                System.out.println("Student with ID " + studentID + " not found.");
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    @Override
    public Student getStudentById(int studentID) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Student.class, studentID);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
	public List<Student> displayAllStudent() {
		try (Session session = sessionFactory.openSession()) {
			String hql="from Student";  
			Query q=session.createQuery(hql);
			List<Student> students=q.getResultList();
			if (students.isEmpty()) {
	            System.out.println("No students found.");
	        } else {
	            System.out.println("List of Students:");
	            System.out.printf("%-10s%-20s%-30s%n", "ID", "Name", "Email");
	            System.out.println("===========================================");
	            for (Student student : students) {
	                System.out.printf("%-10d%-20s%-30s%n", student.getStudentID(), student.getName(), student.getEmail());
	            }
	        }
			return students;
			
	           
	       } catch (Exception e) {
	    	   System.out.println("Display all error");
	           e.printStackTrace();
	       }
		return null;
	}
    @Override
    public Student displayStudentDetails(int studentId) {
        try (Session session = sessionFactory.openSession()) {
            // Retrieve the student details based on the provided studentId
            Student student = session.get(Student.class, studentId);
            
            if (student == null) {
                System.out.println("Student not found.");
            } else {
                System.out.println("Student Details:");
                System.out.println("ID: " + student.getStudentID());
                System.out.println("Name: " + student.getName());
                System.out.println("Email: " + student.getEmail());
            }

            return student;
        } catch (Exception e) {
            System.out.println("Error fetching student details.");
            e.printStackTrace();
        }
        return null;
    }
}
