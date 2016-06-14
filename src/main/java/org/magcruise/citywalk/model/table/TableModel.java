package org.magcruise.citywalk.model.table;

import java.lang.reflect.ParameterizedType;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.magcruise.citywalk.jsonrpc.servlet.ApplicationInitializer;
import org.nkjmlab.util.db.DbClient;

import net.sf.persist.annotations.Table;

public abstract class TableModel<T> {
	protected static org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager
			.getLogger();

	protected abstract String getTableSchema();

	public void createTableIfNotExists(DbClient client) {
		client.createTableIfNotExists(getTableSchema());
	}

	public void dropTableIfExists(DbClient client) {
		client.dropTableIfExists(getTableName());
	}

	public String getTableName() {
		Class<T> clazz = getGenericClass(getClass());
		Table a = clazz.getAnnotation(Table.class);
		if (a != null) {
			return a.name();
		}
		throw new RuntimeException();
	}

	private Class<T> getGenericClass(Class<?> clazz) {
		@SuppressWarnings("unchecked")
		Class<T> result = (Class<T>) ((ParameterizedType) clazz
				.getGenericSuperclass()).getActualTypeArguments()[0];
		return result;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

	protected DbClient getClient() {
		return ApplicationInitializer.getDbClient();
	}

}
