package org.magcruise.citywalk.model.row;

import java.sql.Timestamp;
import java.util.Date;

import org.magcruise.citywalk.model.JsonConstructiveObject;

import net.sf.persist.annotations.NoColumn;

public class RowModel<T extends JsonConstructiveObject<?>>
		extends JsonConstructiveObject<T> {

	private Timestamp created = new Timestamp(new Date().getTime());

	public String getCreated() {
		return created.toString();
	}

	public void setCreated(String created) {
		this.created = Timestamp.valueOf(created);
	}

	@NoColumn
	public Timestamp getCreatedObject() {
		return created;
	}

	// public void setCreatedObject(Timestamp created)を作ると，無理矢理呼ぼうとして死ぬ．

}
