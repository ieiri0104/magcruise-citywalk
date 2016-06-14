package org.magcruise.citywalk.model;

import java.util.ArrayList;
import java.util.List;

public class SelectionTask extends TaskContent {

	private List<String> selections = new ArrayList<>();
	private String answer;

	public SelectionTask() {
	}

	public SelectionTask(List<String> selections, int answerIndex) {
		this.selections.addAll(selections);
		this.answer = selections.get(answerIndex);
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public List<String> getSelections() {
		return selections;
	}

	public void setSelections(List<String> selections) {
		this.selections = selections;
	}

}
