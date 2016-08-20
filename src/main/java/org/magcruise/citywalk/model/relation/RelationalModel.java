package org.magcruise.citywalk.model.relation;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.magcruise.citywalk.jsonrpc.servlet.ApplicationInitializer;
import org.magcruise.citywalk.model.row.RowModel;
import org.nkjmlab.util.db.DbClient;

public abstract class RelationalModel<T extends RowModel<?>> {
	protected static org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager
			.getLogger();

	public static final String ID = "id";
	public static final String NAME = "name";
	public static final String CREATED = "created";
	public static final String INSTANCE_CLASS = "instance_class";
	public static final String CHECKPOINT_GROUP_IDS = "checkpoint_group_ids";
	public static final String CHECKPOINT_IDS = "checkpoint_ids";
	public static final String USER_ID = "user_id";
	public static final String CONTENT = "content";
	public static final String GROUP_ID = "group_id";
	public static final String SAVED = "saved";

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
	public void insertBatch(T... objects) {
		getClient().insertBatch((Object[]) objects);
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
