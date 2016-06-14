package org.magcruise.citywalk.model.table;

import org.magcruise.citywalk.model.row.User;

public class Users extends TableModel<User> {

	public Users() {
	}

	@Override
	protected String getTableSchema() {
		return getTableName() + "(id varchar primary key, group_id varchar)";
	}

}
