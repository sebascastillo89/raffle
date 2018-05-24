package com.softtek.raffle.dto;

public class Raffle {

	private String candidates;
	private String prizes;
	private String awards;
	private String summary;

	public String getCandidates() {
		return candidates;
	}

	public void setCandidates(String candidates) {
		this.candidates = candidates;
	}

	public String getPrizes() {
		return prizes;
	}

	public void setPrizes(String prizes) {
		this.prizes = prizes;
	}

	public String getAwards() {
		return awards;
	}

	public void setAwards(String awards) {
		this.awards = awards;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

}
