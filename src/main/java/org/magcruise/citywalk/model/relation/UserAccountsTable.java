package org.magcruise.citywalk.model.relation;

import org.magcruise.citywalk.model.row.User;

public class UserAccountsTable extends RelationalModel<User> {

	public static final String TABLE_NAME = "USER_ACCOUNTS";

	@Override
	protected String getRelationName() {
		return TABLE_NAME;
	}

	@Override
	protected String getRelationalSchema() {
		return TABLE_NAME + "(" + ID + " varchar primary key, " + CREATED
				+ " TIMESTAMP AS CURRENT_TIMESTAMP NOT NULL, " + INSTANCE_CLASS
				+ " varchar, " + GROUP_ID + " varchar)";
	}

}
