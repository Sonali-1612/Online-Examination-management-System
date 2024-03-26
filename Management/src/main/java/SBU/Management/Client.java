package SBU.Management;
import java.util.Scanner;

import son.com.dao.ExamDAOImpl;
import son.com.dao.Exam_StudentDAOImpl;
import son.com.dao.QuestionsDAOImpl;
//import son.com.dao.ExamDao;
import son.com.dao.StudentDAOImpl;
import son.com.dao.TeacherDAOImpl;
import son.com.model.Exam;
import son.com.model.Exam_Student;
import son.com.model.Student;
import son.com.model.Teacher;
import son.com.service.ExamServiceImpl;
import son.com.service.QuestionServiceImpl;
import son.com.service.StudentServiceImpl;
import son.com.service.TeacherServiceImpl;

public class Client {
	private static int loggedInStudentId;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
        System.out.println("Are you a Teacher or a Student?");
        System.out.println("1. Teacher");
        System.out.println("2. Student");
        System.out.print("Enter your choice: ");
        int userTypeChoice = scanner.nextInt();
        scanner.nextLine();

        switch (userTypeChoice) {
            case 1:
            	teacherLoginOrSignup();
                break;
            case 2:
            	studentLoginOrSignup();
                break;
            default:
                System.out.println("Invalid choice. Exiting the application.");
        }
		
	}
	public static void teacherLoginOrSignup() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to:");
        System.out.println("1. Login");
        System.out.println("2. Sign up");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        TeacherDAOImpl teacherDao = new TeacherDAOImpl();
        TeacherServiceImpl tService = new TeacherServiceImpl(teacherDao);
        Teacher teacher = new Teacher();

        switch (choice) {
            case 1:
            	teacherLogin();
                break;
            case 2:
                // Teacher sign-up logic
                tService.insertTeacher(teacher);
                teacherLogin();
                break;
            default:
                System.out.println("Invalid choice. Exiting the application.");
                break;
        }
    }
	public static void studentLoginOrSignup() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to:");
        System.out.println("1. Login");
        System.out.println("2. Sign up");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        StudentDAOImpl studentdao = new StudentDAOImpl();
        StudentServiceImpl sService = new StudentServiceImpl(studentdao);
        Student student = new Student();

        switch (choice) {
            case 1:
                studentLogin();
                break;
            case 2:
                // Student sign-up logic
                sService.insertStudent(student);
                studentLogin();
                break;
            default:
                System.out.println("Invalid choice. Exiting the application.");
                break;
        }
    }
	public static void runTeacherModule(int teacherID) {
        TeacherDAOImpl teacherDao = new TeacherDAOImpl();
        TeacherServiceImpl tService = new TeacherServiceImpl(teacherDao);
        Teacher teacher = new Teacher();
        //Scanner scanner = new Scanner(System.in);
        StudentDAOImpl studentdao = new StudentDAOImpl();
        StudentServiceImpl sService = new StudentServiceImpl(studentdao);
        ExamDAOImpl examDao = new ExamDAOImpl();
        ExamServiceImpl eService = new ExamServiceImpl(examDao); // Create an instance of ExamServiceImpl
        Student student = new Student();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("========Welcome Teacher!========");
            //System.out.println("1. Insert Details:");
            System.out.println("1. Show Teacher's Detail: ");
            System.out.println("2. Show Student's Detail: ");
            System.out.println("3. Update your Details:");
            System.out.println("4. Delete your Details:");
            System.out.println("5. Delete Student Details:");
            System.out.println("6. Manage exams:");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                	tService.displayAllTeacher();
                    
                    break;
                case 2:
                	sService.displayAllStudent();
                    
                    break;
                case 3:
                	tService.updateTeacher(teacher);
                    break;
                case 4:
                	tService.deleteTeacher(teacher);
                	break;
                case 5:
                	sService.deleteStudent(student);
                	break;
                case 6:
                    manageExamsMenu(teacherID);
                    break;
                case 7:
                    System.out.println("Exiting the application. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    public static void runStudentModule(int studentID) {
    	loggedInStudentId = studentID;
        StudentDAOImpl studentdao = new StudentDAOImpl();
        StudentServiceImpl sService = new StudentServiceImpl(studentdao);
        Student student = new Student();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("========Welcome Student!========");
            //System.out.println("1. Insert Details:");
            
            System.out.println("1. Update your Details:");
            System.out.println("2. Show your Details: ");
            System.out.println("3. Give an exam:");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
               
                case 1:
                    sService.updateStudent(student);
                    break;
                case 2:
                    sService.displayStudentDetails(loggedInStudentId);
                    break;
                case 3:
                	takeExam(studentID); // Call takeExam method
                    break;
                //case 1:
                   // sService.deleteStudent(student);
                   // break;
                case 4:
                    System.out.println("Exiting the application. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
    public static void teacherLogin() {
    	Scanner scanner = new Scanner(System.in);
    	TeacherDAOImpl teacherDao = new TeacherDAOImpl();
        TeacherServiceImpl tService = new TeacherServiceImpl(teacherDao);
        Teacher teacher = new Teacher();
             // Teacher login logic
        System.out.println("Welcome to Teacher Login Page! ");
             System.out.println("Enter Teacher ID: ");
             int teacherId = scanner.nextInt();
             scanner.nextLine(); // Consume newline
             System.out.print("Enter password: ");
             String teacherPassword = scanner.nextLine();

             // Validate credentials
             // Implement your authentication logic here
             //Teacher authenticatedTeacher = tService.getTeacherById(teacherId);
             //if (authenticatedTeacher != null && authenticatedTeacher.getPassword().equals(teacherPassword)) 
             if (tService.authenticateTeacher(teacherId, teacherPassword))
             {
                 System.out.println("Teacher login successful!");
                 runTeacherModule(teacherId);
             } else {
                 System.out.println("Invalid credentials. Exiting the application.");
             }
    }
    public static void studentLogin() {
    	Scanner scanner = new Scanner(System.in);
    	StudentDAOImpl studentdao = new StudentDAOImpl();
        StudentServiceImpl sService = new StudentServiceImpl(studentdao);
        Student student = new Student();
    	// Student login logic
        System.out.println("Welcome to Student Login Page! ");
        System.out.println("Enter Student ID: ");
        int studentId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter password: ");
        String studentPassword = scanner.nextLine();

        // Validate credentials
        // Implement your authentication logic here
        Student authenticatedStudent = sService.getStudentById(studentId);
        if (authenticatedStudent != null && authenticatedStudent.getPassword().equals(studentPassword)) {
            System.out.println("Student login successful!");
            runStudentModule(studentId);
        } else {
            System.out.println("Invalid credentials. Exiting the application.");
        }
    }
    public static void manageExamsMenu(int teacherID) {
        Scanner scanner = new Scanner(System.in);
        ExamDAOImpl examDao = new ExamDAOImpl(); // Create an instance of Exam DAO
        ExamServiceImpl eService = new ExamServiceImpl(examDao); // Create an instance of Exam Service
        
        System.out.println("======== Manage Exams ========");
        System.out.println("1. Insert New Exam");
        System.out.println("2. Update Existing Exam");
        System.out.println("3. Delete Existing Exam");
        System.out.println("4. Manage Questions");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
            	System.out.println("Inserting new exam...");
                eService.insertExam(teacherID); // Pass the teacher ID if needed
                break;
            case 2:
            	
                eService.updateExam(teacherID);
                System.out.println("Updating existing exam...");// Pass the teacher ID if needed
                break;
            case 3:
            	
                eService.deleteExam(teacherID);
                System.out.println("Deleting existing exam...");// Pass the teacher ID if needed
                break;
            case 4:
            	 // Get the exam ID to manage its questions
                System.out.print("Enter the exam ID to manage its questions: ");
                int examID = scanner.nextInt();
                manageQuestionsMenu(examID); // Pass the exam ID
                break;
            default:
                System.out.println("Invalid choice. Please enter a valid option.");
        }
    }
    public static void manageQuestionsMenu(int examID) {
        Scanner scanner = new Scanner(System.in);
        QuestionsDAOImpl questionDao = new QuestionsDAOImpl(); // Create an instance of Question DAO
        QuestionServiceImpl qService = new QuestionServiceImpl(questionDao); // Create an instance of Question Service
        
        System.out.println("======== Manage Questions ========");
        System.out.println("1. Insert New Question");
        System.out.println("2. Update Existing Question");
        System.out.println("3. View All Questions");
        System.out.println("4. Delete Existing Question");
        //System.out.println("5. Back to Exam Menu");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                System.out.println("Enter the number of questions to be inserted:");
                int number = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                for (int i = 0; i < number; i++) {
                    System.out.println("Inserting question " + (i + 1) + " of " + number);
                    qService.addQuestionToExam(examID); // Call the method to add a question to the exam
                } // Pass the exam ID
                break;
            case 2:
                
                qService.updateQuestion(examID); 
                System.out.println("Updating existing question...");// Pass the exam ID
                break;
            case 3:
                System.out.println("Viewing all questions...");
                qService.viewQuestionsInExam(examID); // Pass the exam ID
                break;
            case 4:
                
                qService.deleteQuestion(examID);
                System.out.println("Deleting existing question...");// Pass the exam ID
                break;
            //case 5:
                //return; // Back to exam menu
            default:
                System.out.println("Invalid choice. Please enter a valid option.");
        }
    }
    public static void takeExam(int studentID) {
    	Exam_StudentDAOImpl examStudentDao = new Exam_StudentDAOImpl(); // Create an instance of the DAO
    	Exam_Student examStudent = new Exam_Student();
    	examStudent.setStudentID(studentID);
    	examStudentDao.saveExamAttempt(examStudent);
    }
}
/*
Question: "______ are going to the party tonight."
Options:
a) He and she
b) Him and her
c) They
d) Theirs
Correct option: c) They

Question: "Please pass the book to ______."
Options:
a) I
b) Me
c) Myself
d) Mines
Correct option: b) Me

Question: "______ is a doctor."
Options:
a) He
b) Him
c) His
d) He's
Correct option: a) He

Question: "She said ______ would call you later."
Options:
a) Her
b) She
c) Herself
d) She's
Correct option: b) She

Question: "______ are my best friends."
Options:
a) They
b) Them
c) Their
d) Theirs
Correct option: a) They*/
