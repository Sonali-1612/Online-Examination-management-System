package son.com.dao;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import son.com.model.Exam;
import son.com.model.Teacher;
import son.com.utility.HibernetUtil;

public class ExamDAOImpl implements ExamDao{
	private SessionFactory sessionFactory;

    public ExamDAOImpl() {
        this.sessionFactory = HibernetUtil.getSessionFactory();
    }
    public void insertExam(int teacherID) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            Scanner scanner = new Scanner(System.in);

            // Prompt the user to input the exam ID
            System.out.println("Please set the exam ID(course code):");
            int examID = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
            
            // Prompt the user to input the exam title
            System.out.println("Please set the title of the exam(topic):");
            String examTitle = scanner.nextLine();

            // Get the teacher by ID
            Teacher teacher = session.get(Teacher.class, teacherID);
            if (teacher != null) {
                // Create a new exam and set its ID and title
                Exam exam = new Exam();
                exam.setExamID(examID);
                exam.setTitle(examTitle);

                // Set the teacher for this exam
                exam.setTeacherID(teacherID);

                // Save the exam
                session.save(exam);

                transaction.commit();
                System.out.println("Exam inserted successfully.");
            } else {
                System.out.println("Teacher with ID " + teacherID + " not found.");
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    public void updateExam(int teacherID) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            // Prompt the user to input the exam ID to be updated
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please enter the exam ID to be updated:");
            int examID = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            // Get the existing exam by ID
            Exam exam = session.get(Exam.class, examID);
            if (exam != null && exam.getTeacherID() == teacherID) {
                // Prompt the user to input the updated title of the exam
                System.out.println("Please enter the updated title of the exam:");
                String newTitle = scanner.nextLine();

                // Update the title of the exam
                exam.setTitle(newTitle);

                // Save the updated exam
                session.update(exam);

                transaction.commit();
                System.out.println("Exam updated successfully.");
            } else {
                System.out.println("Either exam with ID " + examID + " not found or you don't have permission to update this exam.");
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteExam(int teacherID) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            // Prompt the user to input the exam ID to be deleted
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please enter the exam ID to be deleted:");
            int examID = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            // Get the existing exam by ID
            Exam exam = session.get(Exam.class, examID);
            if (exam != null && exam.getTeacherID() == teacherID) {
                // Delete the exam
                session.delete(exam);

                transaction.commit();
                System.out.println("Exam deleted successfully.");
            } else {
                System.out.println("Either exam with ID " + examID + " not found or you don't have permission to delete this exam.");
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
