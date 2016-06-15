package org.magcruise.citywalk.model.table;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.magcruise.citywalk.jsonrpc.servlet.ApplicationInitializer;
import org.nkjmlab.util.db.DbClient;

import net.sf.persist.annotations.Table;

public abstract class TableModel<T> {
	protected static org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager
			.getLogger();

	protected String TABLE_NAME = "UNDEFINED";

	public static final String ID = "ID";
	public static final String CREATED = "CREATED";
	public static final String INSTANCE_CLASS = "INSTANCE_CLASS";
	public static final String CHECKPOINT_GROUP_IDS = "CHECKPOINT_GROUP_IDS";
	public static final String CHECKPOINT_IDS = "CHECKPOINT_IDS";
	public static final String USER_ID = "USER_ID";
	public static final String CONTENT = "CONTENT";
	public static final String GROUP_ID = "GROUP_id";

	public TableModel() {
		TABLE_NAME = getTableName();
	}

	protected abstract String getTableSchema();

	public void createTableIfNotExists() {
		getClient().createTableIfNotExists(getTableSchema());
	}

	public void dropTableIfExists() {
		getClient().dropTableIfExists(getTableName());
	}

	public void remakeTable() {
		dropTableIfExists();
		createTableIfNotExists();
	}

	public String getTableName() {
		Class<T> clazz = getGenericClass();
		Table a = clazz.getAnnotation(Table.class);
		if (a != null) {
			return a.name();
		}
		throw new RuntimeException();
	}

	private Class<T> getGenericClass() {
		@SuppressWarnings("unchecked")
		Class<T> result = (Class<T>) ((ParameterizedType) getClass()
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

	public void insert(T object) {
		getClient().insert(object);
	}

	public void merge(T object) {
		getClient().merge(object);

	}

	public void delete() {
		getClient().deleteAll(getTableName());
	}

	public T readByPrimaryKey(Object... primaryKeyValues) {
		return getClient().readByPrimaryKey(getGenericClass(),
				primaryKeyValues);
	}

	public List<T> selectAll() {
		return getClient().selectAll(getGenericClass(), getTableName());
	}

	public Object getLastInsertId(Class<?> object) {
		return getClient().getLastInsertId(object);
	}

}
