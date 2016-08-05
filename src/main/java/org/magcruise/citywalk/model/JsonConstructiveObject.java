package org.magcruise.citywalk.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import net.arnx.jsonic.JSON;
import net.arnx.jsonic.JSONException;
import net.arnx.jsonic.JSONHint;
import net.sf.persist.annotations.NoColumn;

public class JsonConstructiveObject<T extends JsonConstructiveObject<?>> {
	protected static org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager
			.getLogger();

	protected Class<T> instanceClass;

	@SuppressWarnings("unchecked")
	public JsonConstructiveObject() {
		instanceClass = (Class<T>) getClass();
	}

	@JSONHint(ignore = true)
	@NoColumn
	public Class<T> getInstanceClassObject() {
		return instanceClass;
	}

	/**
	 *
	 * @param instanceClass
	 *            Class<T> instanceClassにするとJSON-RPCライブラリでClassが変換できずに死ぬ．
	 */
	@JSONHint(ignore = true)
	public void setInstanceClassObject(String instanceClass) {
		setInstanceClass(instanceClass);
	}

	public String getInstanceClass() {
		return instanceClass.toString().replaceAll("class ", "");
	}

	@SuppressWarnings("unchecked")
	public void setInstanceClass(String instanceClass) {
		try {
			this.instanceClass = (Class<T>) Class
					.forName(instanceClass.replaceAll("class ", ""));
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public String encodeToJson() {
		return JSON.encode(this);
	}

	public static <S extends JsonConstructiveObject<?>> S decodeFromJson(
			Class<S> clazz, String json) {
		return JSON.decode(json, getContentClass(clazz, json));

	}

	public static <S extends JsonConstructiveObject<?>> Class<S> getContentClass(
			Class<S> clazz, String json) {
		try {
			@SuppressWarnings("unchecked")
			Class<S> result = JSON.decode(json, JsonConstructiveObject.class)
					.getInstanceClassObject();
			return result;
		} catch (JSONException e) {
			log.error(e, e);
			return null;
		}
	}

}
