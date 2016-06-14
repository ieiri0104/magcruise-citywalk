package org.magcruise.citywalk.model.row;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.magcruise.citywalk.model.TaskContent;

import jp.go.nict.langrid.repackaged.net.arnx.jsonic.JSON;
import net.sf.persist.annotations.Column;
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

	private long id;
	private List<String> checkpointIds = new ArrayList<>();
	private TaskContent content;

	public Task() {
	}

	public Task(List<String> checkpointIds, TaskContent content) {
		this.checkpointIds.addAll(checkpointIds);
		this.content = content;
	}

	@Column(autoGenerated = true)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "CHECKPOINT_IDS")
	public String getCheckpointIdString() {
		return JSON.encode(checkpointIds);
	}

	public void setCheckpointIdString(String checkpointId) {
		@SuppressWarnings("unchecked")
		List<String> r = JSON.decode(checkpointId, List.class);
		this.checkpointIds = r;
	}

	@Column(name = "CONTENT")
	public String getContentString() {
		return content.toJson();
	}

	public void setContentString(String taskContent) {
		this.content = new TaskContent().fromJson(taskContent);
	}

	public TaskContent getContent() {
		return content;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
