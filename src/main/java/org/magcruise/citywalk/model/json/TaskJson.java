package org.magcruise.citywalk.model.json;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TaskJson {

	private long id;
	private String taskType;
	private String instanceClass;
	private String label;
	private List<String> selections = new ArrayList<>();
	private List<Integer> answerIndexes = new ArrayList<>();
	private List<String> answerTexts = new ArrayList<>();
	private double score;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
	
	public String getInstanceClass() {
		return instanceClass;
	}

	public void setInstanceClass(String instanceClass) {
		this.instanceClass = instanceClass;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<String> getSelections() {
		return selections;
	}

	public void setSelections(List<String> selections) {
		this.selections = selections;
	}

	public List<Integer> getAnswerIndexes() {
		return answerIndexes;
	}

	public void setAnswerIndexes(List<Integer> answerIndexes) {
		this.answerIndexes = answerIndexes;
	}

	public List<String> getAnswerTexts() {
		return answerTexts;
	}

	public void setAnswerTexts(List<String> answerTexts) {
		this.answerTexts = answerTexts;
	}
	
	public double getScore() {
		return score;
	}
	
	public void setScore(double score) {
		this.score = score;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
