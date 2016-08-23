package org.magcruise.citywalk.model.relation;

import org.magcruise.citywalk.model.row.SubmittedActivity;

public class SubmittedActivitiesTable extends ActivitiesTable<SubmittedActivity> {

	public static final String TABLE_NAME = "SUBMITTED_ACTIVITIES";

	public SubmittedActivitiesTable() {
		this.tableName = TABLE_NAME;
	}

}
