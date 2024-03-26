package son.com.dao;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import son.com.model.Exam_Student;
import son.com.model.Questions;
import son.com.utility.HibernetUtil;
public class Exam_StudentDAOImpl implements Exam_StudentDao{
	private SessionFactory sessionFactory;

    public Exam_StudentDAOImpl() {
    	this.sessionFactory = HibernetUtil.getSessionFactory();
    }
    @Override
    public void saveExamAttempt(Exam_Student examStudent) {
    	Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
        	transaction = session.beginTransaction();
            //session.beginTransaction();
            
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the Exam ID: ");
            int examID = scanner.nextInt();

            // Fetch questions by exam ID
            List<Questions> questions = getQuestionsByExamID(examID, session);
            if (questions.isEmpty()) {
                System.out.println("No questions found for the given Exam ID.");
                return; // Exit method if no questions found
            }
            int score = 0;

            for (Questions question : questions) {
                System.out.println("Question: " + question.getQuestionText());
                System.out.println("Options:");
                System.out.println("1. " + question.getOption1());
                System.out.println("2. " + question.getOption2());
                System.out.println("3. " + question.getOption3());
                System.out.println("4. " + question.getOption4());

                System.out.print("Enter your choice (1-4): ");
                int chosenOption = scanner.nextInt();

                if (chosenOption == question.getCorrectOption()) {
                    score++;
                }
            }
            //Exam_Student examStudent = new Exam_Student(studentID, examID, 0, 0, score); // Assuming Exam_Student constructor takes studentID, examID, chosenOption, score
            //saveExamAttempt(examStudent);
            examStudent.setExamID(examID); // Set the exam ID
            examStudent.setScore(score);

            session.saveOrUpdate(examStudent);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Rollback transaction in case of exception
            }
            e.printStackTrace();
        }
    }
    @Override
    public List<Questions> getQuestionsByExamID(int examID, Session session) {
        List<Questions> questions = new ArrayList<>();
        try {
            Query<Questions> query = session.createQuery("FROM Questions WHERE examID = :examID", Questions.class);
            query.setParameter("examID", examID);
            questions = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return questions;
    }
}
