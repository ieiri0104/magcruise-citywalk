package org.magcruise.citywalk.model;

import net.sf.persist.annotations.Table;

/**
 * クライアントアプリとのJSONインタフェースとなるデータ構造． JSONで受け渡しをするクラスはPOJOでなくてはならない．
 * また，ORMのためのオブジェクトにもなっている．
 *
 * @author nkjm
 *
 */
@Table(name = "TASKS")
public class Task {

	protected static org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager
			.getLogger();

	public static final String TABLE_NAME = "TASKS";
	private int id;
	private int checkpointId;
	private String taskContent;

	public Task() {
	}

	public Task(int id, int checkpointId, String taskContent) {
		this.id = id;
		this.checkpointId = checkpointId;
		this.taskContent = taskContent;
	}

	public Task(int id, int checkpointId, TaskContent taskContent) {
		this(id, checkpointId, taskContent.toString());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCheckpointId() {
		return checkpointId;
	}

	public void setCheckpointId(int checkpointId) {
		this.checkpointId = checkpointId;
	}

	public String getTaskContent() {
		return taskContent;
	}

	public void setTaskContent(String taskContent) {
		this.taskContent = taskContent;
	}

}
