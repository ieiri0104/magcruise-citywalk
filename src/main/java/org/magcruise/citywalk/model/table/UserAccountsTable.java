package org.magcruise.citywalk.model.table;

import org.magcruise.citywalk.model.row.User;

public class UserAccountsTable extends TableModel<User> {

	public UserAccountsTable() {
	}

	@Override
	protected String getTableSchema() {
		return getTableName() + "(" + ID + " varchar primary key, "
				+ INSTANCE_CLASS + " varchar, " + GROUP_ID + " varchar)";
	}

}
