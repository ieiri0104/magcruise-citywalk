package org.magcruise.citywalk.model;

import net.sf.persist.annotations.Table;

/**
 * クライアントアプリとのJSONインタフェースとなるデータ構造． JSONで受け渡しをするクラスはPOJOでなくてはならない．
 * また，ORMのためのオブジェクトにもなっている．
 *
 * @author nkjm
 *
 */
@Table(name = "CHECKPOINTS")
public class Checkpoint {

	protected static org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager
			.getLogger();

	public static final String TABLE_NAME = "CHECKPOINTS";
	private int id;
	private int taskId;

	public Checkpoint() {
	}

	public Checkpoint(int id, int taskId, double lat, double lon) {
		this.id = id;
		this.taskId = taskId;
	}

}
