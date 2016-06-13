package org.magcruise.citywalk.model;

import net.sf.persist.annotations.Table;

/**
 * クライアントアプリとのJSONインタフェースとなるデータ構造． JSONで受け渡しをするクラスはPOJOでなくてはならない．
 * また，ORMのためのオブジェクトにもなっている．
 *
 * @author nkjm
 *
 */
@Table(name = "USERS")
public class User extends TableModel {

	/** e-mail address, as a general rule. **/
	private String id;
	private String groupId;

	public User() {
	}

	public User(String userId, String groupId) {
		this.id = userId;
		this.groupId = groupId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	@Override
	protected String getTableSchema() {
		return getTableName();
	}

}
