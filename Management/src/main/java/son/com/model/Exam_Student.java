package son.com.model;
import java.io.Serializable;

import javax.persistence.*;
@Entity
@Table(name = "Exam_Student")
public class Exam_Student implements Serializable{
	@Id
    @Column(nullable = false, length = 30)
    private int studentID;
	@Id
	@Column(nullable = false, length = 30)
	private int examID;
    @Id
    @Column(nullable = false, unique = true, length = 30)
    private int questionID;
    
    @Column(nullable = false, length = 30)
    private int chosenOption;
    
    @Column(nullable = false, length = 30)
    private int score;

	public Exam_Student(int studentID, int examID, int questionID, int chosenOption, int score) {
		super();
		this.studentID = studentID;
		this.examID = examID;
		this.questionID = questionID;
		this.chosenOption = chosenOption;
		this.score = score;
	}

	public Exam_Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getStudentID() {
		return studentID;
	}

	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}

	public int getExamID() {
		return examID;
	}

	public void setExamID(int examID) {
		this.examID = examID;
	}

	public int getQuestionID() {
		return questionID;
	}

	public void setQuestionID(int questionID) {
		this.questionID = questionID;
	}

	public int getChosenOption() {
		return chosenOption;
	}

	public void setChosenOption(int chosenOption) {
		this.chosenOption = chosenOption;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
