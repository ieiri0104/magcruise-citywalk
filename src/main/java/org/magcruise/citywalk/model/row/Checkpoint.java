package org.magcruise.citywalk.model.row;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import net.sf.persist.annotations.Table;

/**
 * クライアントアプリとのJSONインタフェースとなるデータ構造． JSONで受け渡しをするクラスはPOJOでなくてはならない．
 * また，ORMのためのオブジェクトにもなっている．
 *
 * @author nkjm
 *
 */
@Table(name = "CHECKPOINTS")
public class Checkpoint {

	private String id;
	private double lat;
	private double lon;

	public Checkpoint() {
	}

	public Checkpoint(String id, double lat, double lon) {
		this.id = id;
		this.lat = lat;
		this.lon = lon;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
