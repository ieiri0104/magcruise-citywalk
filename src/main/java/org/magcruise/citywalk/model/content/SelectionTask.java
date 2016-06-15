package org.magcruise.citywalk.model.content;

import java.util.ArrayList;
import java.util.List;

public class SelectionTask extends TaskContent {

	private List<String> selections = new ArrayList<>();
	private int answerIndex = -1;

	public SelectionTask() {
	}

	public SelectionTask(String label, List<String> selections, int answerIndex,
			boolean checkIn) {
		super(label, checkIn);
		this.selections.addAll(selections);
		setAnswerIndex(answerIndex);
	}

	public List<String> getSelections() {
		return selections;
	}

	public void setSelections(List<String> selections) {
		this.selections.addAll(selections);
	}

	public int getAnswerIndex() {
		return answerIndex;
	}

	public void setAnswerIndex(int answerIndex) {
		this.answerIndex = answerIndex;
	}

}
