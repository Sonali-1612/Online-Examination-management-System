package son.com.model;
import javax.persistence.*;

@Entity
@Table(name = "student")
public class Student {
	@Id
	@Column(nullable = false, unique = true, length = 30)
	private int studentID;
	@Column(nullable = false, length = 30)
	private String name;
	@Column(nullable = false, unique = true, length = 30)
	private String email;
	@Column(nullable = false, length = 30)
	private String password;
	public Student(int studentID, String name, String email, String password) {
		super();
		this.studentID = studentID;
		this.name = name;
		this.email = email;
		this.password = password;
	}
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getStudentID() {
		return studentID;
	}
	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
