package org.magcruise.citywalk.jsonrpc.api;

import org.magcruise.citywalk.model.json.InitialDataJson;

import jp.go.nict.langrid.commons.rpc.intf.Parameter;

public interface CityWalkServiceInterface extends UserServiceInterface, ActivityServiceInterface {

	InitialDataJson getInitialData(
			@Parameter(sample = "waseda") String checkpointGroupId);

	InitialDataJson getInitialDataFromFile(
			@Parameter(sample = "waseda") String checkpointGroupId);

	boolean validateCheckpointsAndTasksJson(
			@Parameter(sample = "{\"checkpoints\":["
					+ "{\"id\":\"cafeteria\",\"lat\":38.4400,\"lon\":139.11090,\"checkpoint_group_ids\":[\"waseda\"]}],"
					+ "\"tasks\":[" + "{\"checkpoint_ids\":[\"cafeteria\"],"
					+ "\"content\":{\"instanceClass\":\"org.magcruise.citywalk.model.content.PhotoTask\",\"checkin\":true,"
					+ "\"label\":\"表示されている写真と同じ写真を撮って下さい．\",\"answer\":\"task/ieiri_photo_00.jpg\"}},"
					+ "{\"checkpoint_ids\":[\"aed-1\",\"aed-2\",\"aed-3\",\"aed-4\"],"
					+ "\"content\":{\"instanceClass\":\"org.magcruise.citywalk.model.content.QrCodeTask\",\"checkin\":true,"
					+ "\"label\":\"QRコードを撮って下さい．\",\"answer\":\"task/ieiri_qr_code_02.jpg\"}},"
					+ "{\"checkpoint_ids\":[\"cafeteria\"],\"content\":{\"instanceClass\":\"org.magcruise.citywalk.model.content.SelectionTask\","
					+ "\"label\":\"次のうち、理工の学食が発祥の地であるメニューはどれ？\","
					+ "\"selections\":[\"豚玉丼\",\"チキンおろしだれ\",\"カツカレー\",\"ポーク焼肉\"],\"answerIndex\":3}}]}") String json);

}
