package org.magcruise.citywalk.jsonrpc.api;

import org.magcruise.citywalk.model.row.Task;

import jp.go.nict.langrid.commons.rpc.intf.Parameter;

public interface TaskServiceInterface {

	Task[] getTasks(@Parameter(sample = "1") String checkpointId);

}
