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

public class ModelTest {
	protected static org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager
			.getLogger();

	@Before
	public void setUp() throws Exception {
		H2Server.start();
	}

	@Test
	public void test() {
		Checkpoints checkpoints = new Checkpoints();
		checkpoints.remakeTable();
		checkpoints.merge(new Checkpoint("aed-1", 38.4400, 134.11090,
				Arrays.asList("waseda")));
		checkpoints.merge(new Checkpoint("aed-2", 38.4400, 134.11090,
				Arrays.asList("waseda")));
		checkpoints.merge(new Checkpoint("cafeteria", 38.4400, 134.11090,
				Arrays.asList("waseda")));
		checkpoints.merge(new Checkpoint("pc_room", 38.4400, 134.11090,
				Arrays.asList("waseda")));
		log.debug(checkpoints.selectAll());

		Tasks tasks = new Tasks();
		tasks.remakeTable();
		tasks.insert(new Task(Arrays.asList("cafeteria"),
				new SelectionTask("次のうち、理工の学食が発祥の地であるメニューはどれ？",
						Arrays.asList("豚玉丼", "チキンおろしだれ", "カツカレー", "ポーク焼肉"), 3,
						false)));
		tasks.insert(new Task(
				Arrays.asList("aed-1", "aed-2", "aed-3", "aed-4", "aed-5",
						"aed-6"),
				new SelectionTask("次のうち、AEDの使い方として、間違っているのはどれ？",
						Arrays.asList("パッドは右胸と左わき腹に貼る", "心電図解析中は体に触らない",
								"放電ボタンを押す時は、体から離れる", "呼吸が戻ったらパッドを速やかにはずす"),
						3, false)));
		tasks.insert(new Task(Arrays.asList("pc_room"),
				new SelectionTask("次の4つの部屋を、座席の多い順に並び替えて下さい．",
						Arrays.asList("A", "C", "E", "G"), 1, false)));

		log.debug(tasks.getTasks());

	}

}
