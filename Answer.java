package com.project.tables;

public class Answer {
      private int qno;
      private String qtext;
      private String submittedAnswer;
      private String originalAnswer;
	public Answer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Answer(int qno, String qtext, String submittedAnswer, String originalAnswer) {
		super();
		this.qno = qno;
		this.qtext = qtext;
		this.submittedAnswer = submittedAnswer;
		this.originalAnswer = originalAnswer;
	}
	public int getQno() {
		return qno;
	}
	public void setQno(int qno) {
		this.qno = qno;
	}
	public String getQtext() {
		return qtext;
	}
	public void setQtext(String qtext) {
		this.qtext = qtext;
	}
	public String getSubmittedAnswer() {
		return submittedAnswer;
	}
	public void setSubmittedAnswer(String submittedAnswer) {
		this.submittedAnswer = submittedAnswer;
	}
	public String getOriginalAnswer() {
		return originalAnswer;
	}
	public void setOriginalAnswer(String originalAnswer) {
		this.originalAnswer = originalAnswer;
	}
	@Override
	public String toString() {
		return "Answer [qno=" + qno + ", qtext=" + qtext + ", submittedAnswer=" + submittedAnswer + ", originalAnswer="
				+ originalAnswer + "]";
	}
      
      
}
