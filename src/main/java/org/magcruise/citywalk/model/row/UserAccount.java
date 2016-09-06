package org.magcruise.citywalk.model.row;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.magcruise.citywalk.model.relation.UserAccountsTable;

import net.sf.persist.annotations.Table;

@Table(name = UserAccountsTable.TABLE_NAME)
public class UserAccount {

	private String id;
	private String groupId;
	private Date created = new Timestamp(new Date().getTime());

	public UserAccount() {
	}

	public UserAccount(String userId, String groupId) {
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
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

}
