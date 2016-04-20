package org.nkjmlab.util.rdb;

import java.sql.Connection;
import java.sql.SQLException;

import org.h2.jdbcx.JdbcConnectionPool;

/**
 * コネクションプールを使ったRDBUtil
 *
 * @author nkjm
 *
 */
public class RDBUtilWithConnectionPool extends RDBUtil {

	private final JdbcConnectionPool connectionPool;

	public RDBUtilWithConnectionPool(String jdbcURL) {
		this(jdbcURL, "sa", "");
	}

	public RDBUtilWithConnectionPool(String jdbcURL, String username,
			String password) {
		this(new RDBConfig(jdbcURL, username, password));
	}

	public RDBUtilWithConnectionPool(RDBConfig conf) {
		super(conf);
		this.connectionPool = JdbcConnectionPool.create(conf.getJdbcURL(),
				conf.getUsername(), conf.getPassword());
	}

	@Override
	public Connection getConnection() throws SQLException {
		return connectionPool.getConnection();
	}

}
