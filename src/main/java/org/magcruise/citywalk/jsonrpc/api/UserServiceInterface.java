package org.magcruise.citywalk.jsonrpc.api;

import jp.go.nict.langrid.commons.rpc.intf.Parameter;

public interface UserServiceInterface {

	boolean login(@Parameter(sample = "ayaki") String userId,
			@Parameter(sample = "houchimin") String groupId);

}
