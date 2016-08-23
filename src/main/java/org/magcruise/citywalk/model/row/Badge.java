package org.magcruise.citywalk.model.row;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.magcruise.citywalk.model.relation.BadgesTable;

import net.sf.persist.annotations.Column;
import net.sf.persist.annotations.Table;

@Table(name = BadgesTable.TABLE_NAME)
public class Badge extends RowModel<Badge> {

	private long id;
	private String userId;
	private String badge;

	public Badge() {
	}

	public Badge(String userId, String badge) {
		this.userId = userId;
		this.badge = badge;
	}

	@Column(autoGenerated = true)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBadge() {
		return badge;
	}

	public void setBadge(String badges) {
		this.badge = badges;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
