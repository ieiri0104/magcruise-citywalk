package org.magcruise.citywalk.model.row;

import java.sql.Timestamp;
import java.util.Date;

import org.magcruise.citywalk.model.common.JsonConstructiveObject;

public abstract class RowModel<T extends JsonConstructiveObject<?>>
		extends JsonConstructiveObject<T> {

	private Date created = new Timestamp(new Date().getTime());

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

}
