package org.magcruise.citywalk.model.relation;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.magcruise.citywalk.jsonrpc.servlet.ApplicationInitializer;
import org.magcruise.citywalk.model.row.RowModel;
import org.nkjmlab.util.db.DbClient;

import net.sf.persist.annotations.Table;

public abstract class RelationalModel<T extends RowModel<?>> {
	protected static org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager
			.getLogger();

	public static final String ID = "ID";
	public static final String CREATED = "CREATED";
	public static final String INSTANCE_CLASS = "INSTANCE_CLASS";
	public static final String CHECKPOINT_GROUP_IDS = "CHECKPOINT_GROUP_IDS";
	public static final String CHECKPOINT_IDS = "CHECKPOINT_IDS";
	public static final String USER_ID = "USER_ID";
	public static final String CONTENT = "CONTENT";
	public static final String GROUP_ID = "GROUP_id";
	public static final String SAVED = "SAVED";

	public RelationalModel() {
	}

	protected abstract String getRelationalSchema();

	protected abstract String getRelationName();

	public void createTableIfNotExists() {
		getClient().createTableIfNotExists(getRelationalSchema());
	}

	public void dropTableIfExists() {
		getClient().dropTableIfExists(getRelationName());
	}

	public void remakeTable() {
		dropTableIfExists();
		createTableIfNotExists();
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

	@SuppressWarnings("unchecked")
	public void mergeBatch(T... objects) {
		getClient().mergeBatch((Object[]) objects);
	}

	public void delete() {
		getClient().deleteAll(getRelationName());
	}

	public T readByPrimaryKey(Object... primaryKeyValues) {
		return getClient().readByPrimaryKey(getGenericClass(),
				primaryKeyValues);
	}

	public List<T> selectAll() {
		return getClient().selectAll(getGenericClass(), getRelationName());
	}

	public Object getLastInsertId(Class<?> object) {
		return getClient().getLastInsertId(object);
	}

}
