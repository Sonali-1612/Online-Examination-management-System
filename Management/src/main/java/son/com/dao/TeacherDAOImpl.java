package son.com.dao;
import java.util.List;
import java.util.Scanner;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
//import org.hibernate.query.Query;

import son.com.model.Teacher;
import son.com.utility.HibernetUtil;

public class TeacherDAOImpl implements TeacherDao{
	private SessionFactory sessionFactory;
	public TeacherDAOImpl() {
        this.sessionFactory = HibernetUtil.getSessionFactory();
    }
	@Override
	public void insertTeacher(Teacher teacher) {
   	 Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter TeacherID:");
            int teacherID = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over
            System.out.println("Enter name:");
            String name = scanner.nextLine();
            System.out.println("Enter email:");
            String email = scanner.nextLine();
            System.out.println("Enter password:");
            String password = scanner.nextLine();

            Teacher teacher1 = new Teacher(teacherID, name, email, password);
            session.save(teacher1);

            transaction.commit();
            System.out.println("Teacher inserted successfully.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
   }
	@Override
	public void updateTeacher(Teacher teacher) {
    	Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter TeacherID to update:");
            int studentID = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over
            Teacher teacher1 = session.get(Teacher.class, studentID);
            if (teacher1 != null) {
                System.out.println("Enter updated name:");
                String name = scanner.nextLine();
                System.out.println("Enter updated email:");
                String email = scanner.nextLine();
                System.out.println("Enter updated password:");
                String password = scanner.nextLine();

                teacher1.setName(name);
                teacher1.setEmail(email);
                teacher1.setPassword(password);

                session.update(teacher1);
                transaction.commit();
                System.out.println("Teacher updated successfully.");
            } else {
                System.out.println("Teacher with ID " + studentID + " not found.");
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
	@Override
	public void deleteTeacher(Teacher teacher) {
    	Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter TeacherID to delete:");
            int teacherID = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over
            Teacher teacher1 = session.get(Teacher.class, teacherID);
            if (teacher1 != null) {
                session.delete(teacher1);
                transaction.commit();
                System.out.println("Teacher deleted successfully.");
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
	@Override
	public Teacher getTeacherById(int teacherID) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Teacher.class, teacherID);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	//@Override
	//public List<Teacher> displayAllTeacher() {
		//List<Teacher> teachers = null;
	
        //try (Session session = sessionFactory.openSession()) {
        	//String hql="from Teacher";
        	//Query q = session.createQuery(hql);
        	//teachers = q.getResultList();
        	//System.out.println(teachers);
            //List<Teacher> teachers = session.createQuery("from Teacher", Teacher.class).list();
            //if (teachers.isEmpty()) {
                //System.out.println("No teacher found.");
            //} else {
                //System.out.println("Teachers:");
                //for (Teacher teacher : teachers) {
                    //System.out.println("ID: " + teacher.getTeacherID() + ", Name: " + teacher.getName() +
                           // ", Email: " + teacher.getEmail() + ", Password: " + teacher.getPassword());
               // }
           //}
       // }
    //}
        //catch (Exception e) {
            //e.printStackTrace();
        //}/
        //return teachers;
	//display All customer 
		@Override
		public List<Teacher> displayAllTeacher() {
			try (Session session = sessionFactory.openSession()) {
				String hql="from Teacher";  
				Query q=session.createQuery(hql);
				List<Teacher> teachers=q.getResultList();
//				System.out.println(customer);
				//for (Teacher teacher1 : teacher) {
	                //System.out.println("ID: " + customer1.getCustomerId() + ", Name: " + customer1.getCustomerName() +
	                    //    ", Email: " + customer1.getCustomerEmail() + ", Address: " + customer1.getCustomerAddress()+
	                	//			"Number: " +customer1.getCustomerNumber());
	          //  }
			if (teachers.isEmpty()) {
		            System.out.println("No teachers found.");
		        } else {
		            System.out.println("List of Teachers:");
		            System.out.printf("%-10s%-20s%-30s%n", "ID", "Name", "Email");
		            System.out.println("===========================================");
		            for (Teacher teacher : teachers) {
		                System.out.printf("%-10d%-20s%-30s%n", teacher.getTeacherID(), teacher.getName(), teacher.getEmail());
		            }
		        }
				return teachers;  
		       } catch (Exception e) {
		    	   System.out.println("Display all error");
		           e.printStackTrace();
		       }
			return null;
		}	
}
