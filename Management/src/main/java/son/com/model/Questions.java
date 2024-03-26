package son.com.model;

import javax.persistence.*;

@Entity
//@Table(name = "Questions")
public class Questions {
	@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true, length = 30)
    private int questionID;

    @Column(nullable = false, unique = true, length = 30)
    private int examID;

    @Column(nullable = false, length = 30)
    private String questionText;

    @Column(nullable = false, length = 30)
    private String option1;

    @Column(nullable = false, length = 30)
    private String option2;

    @Column(nullable = false, length = 30)
    private String option3;

    @Column(nullable = false, length = 30)
    private String option4;

    @Column(nullable = false, length = 30)
    private int correctOption;

	public Questions(int questionID, int examID, String questionText, String option1, String option2, String option3,
			String option4, int correctOption) {
		super();
		this.questionID = questionID;
		this.examID = examID;
		this.questionText = questionText;
		this.option1 = option1;
		this.option2 = option2;
		this.option3 = option3;
		this.option4 = option4;
		this.correctOption = correctOption;
	}

	public Questions() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getQuestionID() {
		return questionID;
	}

	public void setQuestionID(int questionID) {
		this.questionID = questionID;
	}

	public int getExamID() {
		return examID;
	}

	public void setExamID(Exam exam) {
        this.examID = exam.getExamID();
    }
	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public String getOption1() {
		return option1;
	}

	public void setOption1(String option1) {
		this.option1 = option1;
	}

	public String getOption2() {
		return option2;
	}

	public void setOption2(String option2) {
		this.option2 = option2;
	}

	public String getOption3() {
		return option3;
	}

	public void setOption3(String option3) {
		this.option3 = option3;
	}

	public String getOption4() {
		return option4;
	}

	public void setOption4(String option4) {
		this.option4 = option4;
	}

	public int getCorrectOption() {
		return correctOption;
	}

	public void setCorrectOption(int correctOption) {
		this.correctOption = correctOption;
	}
}
