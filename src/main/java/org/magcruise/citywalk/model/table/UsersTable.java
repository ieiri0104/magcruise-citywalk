package org.magcruise.citywalk.model.table;

import org.magcruise.citywalk.model.row.User;

public class UsersTable extends TableModel<User> {

	public UsersTable() {
	}

	@Override
	protected String getTableSchema() {
		return getTableName() + "(id varchar primary key, "
				+ "instance_class varchar, " + "group_id varchar)";
	}

}
