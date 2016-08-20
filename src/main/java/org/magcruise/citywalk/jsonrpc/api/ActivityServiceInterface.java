package org.magcruise.citywalk.jsonrpc.api;

import org.magcruise.citywalk.model.json.ActivityJson;
import org.magcruise.citywalk.model.json.ActivityLogJson;
import org.magcruise.citywalk.model.json.RewardJson;

import jp.go.nict.langrid.commons.rpc.intf.Parameter;

public interface ActivityServiceInterface {

	RewardJson addActivity(
			@Parameter(sample = "{\"userId\": \"ayaki\", " + "\"checkpointId\": \"1\", "
					+ "\"taskId\": \"1\", " + "\"score\": 9.0, " + "\"inputs\": "
					+ "{\"value\":\"1\"}}") ActivityJson json);

	ActivityLogJson[] getActivityLogs(@Parameter(sample = "Yuya Ieiri") String userId);

	ActivityLogJson[] getNewActivityLogsOrderById(
			@Parameter(sample = "Yuya Ieiri") String userId,
			@Parameter(sample = "1") long latestActivityId);

	String uploadImage(
			@Parameter(sample = "ayaki") String userId,
			@Parameter(sample = "data:image/jpeg;base64,"
					+ "/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDAAYEBQYFBAYGBQYHBwYIChAKCgkJ"
					+ "ChQODwwQFxQYGBcUFhYaHSUfGhsjHBYWICwgIyYnKSopGR8tMC0oMCUoKSj/"
					+ "2wBDAQcHBwoIChMKChMoGhYaKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgo"
					+ "KCgoKCgoKCgoKCgoKCgoKCgoKCgoKCj/"
					+ "wAARCAATABIDASIAAhEBAxEB/8QAGAABAQEBAQAAAAAAAAAAAAAAAAYFBwT/"
					+ "xAAqEAACAQIEBQMFAQAAAAAAAAABAgMEEQAFBiESEzFBYSJRcQcUFkKi8f/"
					+ "EABYBAQEBAAAAAAAAAAAAAAAAAAMEBf/"
					+ "EAB0RAQACAQUBAAAAAAAAAAAAAAEAEQIDEhMhUSL/" + "2gAMAwEAAhEDEQA/"
					+ "ANDU+t6vJ5+RSQM9W6iWQzSKVQHpchd9h0Ht5x7tK6vqc3najzSGemmKXR40DRyW6/"
					+ "qbHvY/7z7UjU0urphJUwugaLhAa/"
					+ "qESgrfwwO19rdOltv6dAJqWohNW8CrEzLE7X5hJBJF+9l87A+bZG54yjurmrxFOa9X"
					+ "Kr8s09H6BncyhdgOW+384Y5stIiqBPlQaUCzs00yknuSBsPgYYprL1"
					+ "knx5IrMpXTOOFCFChCLADc7kn3xY6Zq5353E+8dPNVIeEArKiNwsPiw26YYYr1AMFg6b"
					+ "bTJ+uzGp+9qLNGBzG2WJABv2AG2GGGCjT/2Q==") String base64EncodedImage);

}
