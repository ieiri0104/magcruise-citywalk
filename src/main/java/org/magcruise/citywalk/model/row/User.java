package org.magcruise.citywalk.model.row;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import net.sf.persist.annotations.Table;

/**
 * クライアントアプリとのJSONインタフェースとなるデータ構造． JSONで受け渡しをするクラスはPOJOでなくてはならない．
 * また，ORMのためのオブジェクトにもなっている．
 *
 * @author nkjm
 *
 */
@Table(name = "USERS")
public class User {

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
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
