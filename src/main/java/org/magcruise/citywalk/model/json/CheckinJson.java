package org.magcruise.citywalk.model.json;

public class CheckinJson {

	private long id;
	private String checkinType;
	private String checkinAnswerQr;
	private double score;

	public String getCheckinType() {
		return checkinType;
	}

	public void setCheckinType(String checkinType) {
		this.checkinType = checkinType;
	}

	public String getCheckinAnswerQr() {
		return checkinAnswerQr;
	}

	public void setCheckinAnswerQr(String checkinAnswerQr) {
		this.checkinAnswerQr = checkinAnswerQr;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

}
