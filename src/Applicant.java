
public class Applicant implements Comparable<Applicant> {
	private String name;
	private int score;
	private int education;
	private int experience;
	private int estimatedProfit[];
	private int loan;


	public Applicant(String name, int education, int experience, int[] estimatedProfit, int score,int loan) {
		this.setName(name);
		this.setEducation(education);
		this.setExperience(experience);
		this.estimatedProfit=this.estimatedProfit;
		this.setScore(score);
		this.setLoan(loan);
		
	}
	/**
	 * Compares the score
	 */
	public int compareTo(Applicant other){
		if (this.score > other.score)
			return 1;
		else if (this.score < other.score)
			return -1;
		else
			return 0;
		// you can just write it in one line:
		// return this.score - other.score
	}
	
	// this method will be used to find an applicant
	public boolean equals(Applicant other) {
		return this.name.equals(other.name);
	}
	
	public String toString() {
		//complete this method
		//it will be useful for print option in class Loan 
		return "";
	}
	public int getEducation() {
		return education;
	}
	public void setEducation(int education) {
		this.education = education;
	}
	public int getExperience() {
		return experience;
	}
	public void setExperience(int experience) {
		this.experience = experience;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getLoan() {
		return loan;
	}
	public void setLoan(int loan) {
		this.loan = loan;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
}
