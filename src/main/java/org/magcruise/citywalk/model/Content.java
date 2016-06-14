package org.magcruise.citywalk.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import jp.go.nict.langrid.repackaged.net.arnx.jsonic.JSONException;
import net.arnx.jsonic.JSON;

public class Content<T> {
	protected static org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager
			.getLogger();

	protected String instanceClass;

	public Content() {
		instanceClass = getClass().getName();
	}

	public String getInstanceClass() {
		return instanceClass;
	}

	public void setInstanceClass(String instanceClass) {
		this.instanceClass = instanceClass;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public String toJson() {
		return JSON.encode(this);
	}

	public T fromJson(String taskContent) {
		return JSON.decode(taskContent, getContentClass(taskContent));

	}

	public Class<T> getContentClass(String taskContent) {
		try {
			@SuppressWarnings("unchecked")
			Class<T> result = (Class<T>) Class.forName(JSON
					.decode(taskContent, TaskContent.class).getInstanceClass());
			return result;
		} catch (ClassNotFoundException e) {
			log.error(e, e);
			return null;
		} catch (JSONException e) {
			log.error(e, e);
			return null;
		}
	}

}
