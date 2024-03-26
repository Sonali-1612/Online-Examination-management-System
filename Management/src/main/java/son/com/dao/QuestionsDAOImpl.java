package son.com.dao;
import java.util.List;
import java.util.Scanner;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import son.com.model.Exam;
import son.com.model.Questions;
import son.com.utility.HibernetUtil;

public class QuestionsDAOImpl implements QuestionDao{
	private SessionFactory sessionFactory;

	public QuestionsDAOImpl() {
        this.sessionFactory = HibernetUtil.getSessionFactory();
    }

    @Override
    public void addQuestionToExam(int examID) {
        //Session session = sessionFactory.openSession();
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()){
            tx = session.beginTransaction();

            // Retrieve the exam associated with the given examID
            Exam exam = session.get(Exam.class, examID);

            if (exam != null) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Exam Title: " + exam.getTitle());
                

                Questions question = new Questions();

                System.out.print("Please enter the question ID: ");
                int questionID = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                question.setQuestionID(questionID);

                System.out.print("Please enter the question text: ");
                String questionText = scanner.nextLine();
                question.setQuestionText(questionText);

                System.out.print("Please enter option 1: ");
                String option1 = scanner.nextLine();
                question.setOption1(option1);

                System.out.print("Please enter option 2: ");
                String option2 = scanner.nextLine();
                question.setOption2(option2);

                System.out.print("Please enter option 3: ");
                String option3 = scanner.nextLine();
                question.setOption3(option3);

                System.out.print("Please enter option 4: ");
                String option4 = scanner.nextLine();
                question.setOption4(option4);

                System.out.print("Please enter the correct option (1-4): ");
                int correctOption = scanner.nextInt();
                question.setCorrectOption(correctOption);

                // Set the exam for the question
                question.setExamID(exam);

                // Add the question to the exam's list of questions
                //exam.getQuestions().add(question);

                // Save the question
                session.save(question);
                System.out.println("Question added successfully.");
            } else {
                System.out.println("Exam with ID " + examID + " does not exist.");
            }
            //System.out.println("Adding a new question to the exam...");
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            System.err.println("Error adding question to exam: " + e.getMessage());
        } //finally {
            //session.close();
        //}
    }
    @Override
    public void updateQuestion(int examID) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            // Retrieve the exam associated with the given examID
            Exam exam = session.get(Exam.class, examID);

            if (exam != null) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Exam Title: " + exam.getTitle());
                System.out.println("Updating a question for the exam...");

                System.out.print("Please enter the question ID: ");
                int questionID = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                
                // Retrieve the question associated with the given questionID
                Questions question = session.get(Questions.class, questionID);
                
                if (question != null) {
                    System.out.print("Please enter the updated question text: ");
                    String questionText = scanner.nextLine();
                    question.setQuestionText(questionText);

                    System.out.print("Please enter updated option 1: ");
                    String option1 = scanner.nextLine();
                    question.setOption1(option1);

                    System.out.print("Please enter updated option 2: ");
                    String option2 = scanner.nextLine();
                    question.setOption2(option2);

                    System.out.print("Please enter updated option 3: ");
                    String option3 = scanner.nextLine();
                    question.setOption3(option3);

                    System.out.print("Please enter updated option 4: ");
                    String option4 = scanner.nextLine();
                    question.setOption4(option4);

                    System.out.print("Please enter the correct option (1-4): ");
                    int correctOption = scanner.nextInt();
                    question.setCorrectOption(correctOption);

                    // Save the updated question
                    session.update(question);
                    System.out.println("Question updated successfully.");
                } else {
                    System.out.println("Question with ID " + questionID + " does not exist.");
                }
            } else {
                System.out.println("Exam with ID " + examID + " does not exist.");
            }

            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    @Override
    public void deleteQuestion(int questionID) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            // Retrieve the question associated with the given questionID
            Questions question = session.get(Questions.class, questionID);

            if (question != null) {
                // Delete the question
                session.delete(question);
                System.out.println("Question with ID " + questionID + " deleted successfully.");
            } else {
                System.out.println("Question with ID " + questionID + " does not exist.");
            }

            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    @Override
    public void viewQuestionsInExam(int examID) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();

            // Retrieve the exam associated with the given examID
            Exam exam = session.get(Exam.class, examID);

            if (exam != null) {
                // Retrieve all questions for the given examID
                CriteriaBuilder builder = session.getCriteriaBuilder();
                CriteriaQuery<Questions> query = builder.createQuery(Questions.class);
                Root<Questions> root = query.from(Questions.class);
                query.select(root).where(builder.equal(root.get("examID"), exam));

                List<Questions> questionsList = session.createQuery(query).getResultList();

                if (questionsList.isEmpty()) {
                    System.out.println("No questions found for exam with ID " + examID);
                } else {
                    System.out.println("Questions for exam ID " + examID + ":");
                    for (Questions question : questionsList) {
                        System.out.println("Question ID: " + question.getQuestionID());
                        System.out.println("Question Text: " + question.getQuestionText());
                        System.out.println("Option 1: " + question.getOption1());
                        System.out.println("Option 2: " + question.getOption2());
                        System.out.println("Option 3: " + question.getOption3());
                        System.out.println("Option 4: " + question.getOption4());
                        System.out.println("Correct Option: " + question.getCorrectOption());
                        System.out.println();
                    }
                }
            } else {
                System.out.println("Exam with ID " + examID + " does not exist.");
            }

            tx.commit();
        } catch (HibernateException e) {
            System.out.println("Error while viewing questions for exam with ID " + examID);
            e.printStackTrace();
        }
    }
}
