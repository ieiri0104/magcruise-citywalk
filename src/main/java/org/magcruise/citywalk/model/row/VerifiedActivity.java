package org.magcruise.citywalk.model.row;

import org.magcruise.citywalk.model.input.Input;
import org.magcruise.citywalk.model.json.ActivityJson;
import org.magcruise.citywalk.model.relation.VerifiedActivitiesTable;

import net.sf.persist.annotations.Table;

@Table(name = VerifiedActivitiesTable.TABLE_NAME)
public class VerifiedActivity extends Activity {
	public VerifiedActivity() {
	}

	public VerifiedActivity(String userId, String checkpointId, double lat, double lon,
			String taskId,
			double score, Input input) {
		super(userId, checkpointId, lat, lon, taskId, score, input);
	}

	public VerifiedActivity(ActivityJson json) {
		super(json);
	}

}
