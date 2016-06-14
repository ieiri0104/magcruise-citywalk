package org.magcruise.citywalk.model;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.magcruise.citywalk.model.row.Task;
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
				Arrays.asList("豚玉丼", "チキンおろしだれ", "カツカレー", "ポーク焼肉"), 3));
		tasks.insert(t);

		System.out.println(tasks.readList("Select * from Tasks"));
	}

}