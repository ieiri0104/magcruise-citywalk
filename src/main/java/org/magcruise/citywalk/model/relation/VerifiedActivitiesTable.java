package org.magcruise.citywalk.model.relation;

import java.util.List;
import java.util.Map;

public class VerifiedActivitiesTable extends ActivitiesTable {

	public static final String TABLE_NAME = "ACTIVITIES";

	public VerifiedActivitiesTable() {
		this.tableName = TABLE_NAME;
	}

	public int getRank(String userId) {
		List<Map<String, Object>> scores = sumsOfScoreGroupByUserIdOrderByScore();
		for (int i = 0; i < scores.size(); i++) {
			if (scores.get(i).get(USER_ID).equals(userId)) {
				return i+1;
			}

		}
		return -1;
	}

}
