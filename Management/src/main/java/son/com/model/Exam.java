package son.com.model;
import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "exam")
public class Exam {
	@Id
	@Column(nullable = false, unique = true, length = 30)
	private int examID;
	@Column(nullable = false, unique = true, length = 30)
    private String title;
	@Column(nullable = false, unique = true, length = 30)
    private int teacherID;
	public Exam(int examID, String title, int teacherID) {
		super();
		this.examID = examID;
		this.title = title;
		this.teacherID = teacherID;
	}
	public Exam() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getExamID() {
		return examID;
	}
	public void setExamID(int examID) {
		this.examID = examID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getTeacherID() {
		return teacherID;
	}
	public void setTeacherID(int teacherID) {
		this.teacherID = teacherID;
	}


}
