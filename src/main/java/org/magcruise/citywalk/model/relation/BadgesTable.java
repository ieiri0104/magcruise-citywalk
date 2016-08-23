package org.magcruise.citywalk.model.relation;

import org.magcruise.citywalk.model.row.Badge;

public class BadgesTable extends RelationalModel<Badge> {

	public static final String TABLE_NAME = "BADGES";

	@Override
	protected String getRelationName() {
		return TABLE_NAME;
	}

	@Override
	protected String getRelationalSchema() {
		return getRelationName() + "(" + ID + " BIGINT PRIMARY KEY AUTO_INCREMENT, " + CREATED
				+ " TIMESTAMP AS CURRENT_TIMESTAMP NOT NULL, " + INSTANCE_CLASS + " VARCHAR, "
				+ USER_ID + " VARCHAR, " + BADGE + " VARCHAR)";
	}

	public boolean contains(String userId, String badge) {
		return getClient()
				.readList(Badge.class, "SELECT * FROM " + getRelationName() + " WHERE " + USER_ID
						+ "=? AND " + BADGE + "=?", userId, badge)
				.size() > 0;
	}

}
