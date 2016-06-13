package org.magcruise.citywalk.model;

import org.nkjmlab.util.db.DbClient;

import net.sf.persist.annotations.Table;

public abstract class TableModel {
	protected static org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager
			.getLogger();

	public void createTableIfNotExists(DbClient client) {
		client.createTableIfNotExists(getTableSchema());
	}

	public String getTableName() {
		Table a = getClass().getAnnotation(Table.class);
		if (a != null) {
			return a.name();
		}
		throw new RuntimeException();
	}

	protected abstract String getTableSchema();
}
