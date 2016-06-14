package org.magcruise.citywalk.model;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.magcruise.citywalk.model.content.SelectionTask;
import org.magcruise.citywalk.model.row.Checkpoint;
import org.magcruise.citywalk.model.row.Task;
import org.magcruise.citywalk.model.table.Checkpoints;
import org.magcruise.citywalk.model.table.Tasks;
import org.nkjmlab.util.db.H2Server;

public class TaskTest {

	@Before
	public void setUp() throws Exception {
		H2Server.start();
	}

	@Test
	public void test() {
		Tasks tasks = new Tasks();
		Task t = new Task(Arrays.asList("meal"), new SelectionTask(
				Arrays.asList("豚玉丼", "チキンおろしだれ", "カツカレー", "ポーク焼肉"), 3, false));
		tasks.insert(t);
		System.out.println(tasks.getTasks());

		Checkpoints checkpoints = new Checkpoints();
		checkpoints.addCheckpoint(new Checkpoint("aed", 38.4400, 134.11090,
				Arrays.asList("waseda")));
		System.out.println(checkpoints.getCheckPoints());

	}

}
