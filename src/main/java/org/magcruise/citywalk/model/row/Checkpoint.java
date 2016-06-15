package org.magcruise.citywalk.model.row;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.magcruise.citywalk.model.table.TableModel;

import jp.go.nict.langrid.repackaged.net.arnx.jsonic.JSON;
import net.sf.persist.annotations.Column;
import net.sf.persist.annotations.Table;

@Table(name = "CHECKPOINTS")
public class Checkpoint extends RowModel<Checkpoint> {

	private String id;
	private double lat;
	private double lon;
	private List<String> checkpointGroupIds = new ArrayList<>();

	public Checkpoint() {
	}

	public Checkpoint(String id, double lat, double lon,
			List<String> checkPointGroupIds) {
		this.id = id;
		this.lat = lat;
		this.lon = lon;
		this.checkpointGroupIds.addAll(checkPointGroupIds);
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

	@Column(name = TableModel.CHECKPOINT_GROUP_IDS)
	public String getCheckpointGroupIdsString() {
		return JSON.encode(checkpointGroupIds);
	}

	public void setCheckpointGroupIdsString(String checkpointGroupIds) {
		@SuppressWarnings("unchecked")
		List<String> r = JSON.decode(checkpointGroupIds, List.class);
		this.checkpointGroupIds.addAll(r);
	}

	public List<String> getCheckpointGroupIds() {
		return checkpointGroupIds;
	}

	public void setCheckpointGroupIds(List<String> checkpointGroupIds) {
		this.checkpointGroupIds = checkpointGroupIds;
	}

}
