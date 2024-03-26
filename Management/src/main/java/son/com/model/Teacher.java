package son.com.model;
import javax.persistence.*;

@Entity
@Table(name = "Teacher")
public class Teacher {
	@Id
	@Column(nullable = false, unique = true, length = 30)
	private int teacherID;
	@Column(nullable = false, length = 30)
	private String name;
	@Column(nullable = false, unique = true, length = 30)
	private String email;
	@Column(nullable = false, length = 30)
	private String password;
	public Teacher(int teacherID, String name, String email, String password) {
		super();
		this.teacherID = teacherID;
		this.name = name;
		this.email = email;
		this.password = password;
	}
	public Teacher() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getTeacherID() {
		return teacherID;
	}
	public void setTeacherID(int teacherID) {
		this.teacherID = teacherID;
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
