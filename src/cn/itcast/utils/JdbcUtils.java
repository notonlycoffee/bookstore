package cn.itcast.utils;

import java.sql.Connection;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtils {

	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	private static DataSource ds;
	private static DataSource ds_bak;

	static {
		ds = new ComboPooledDataSource();
		ds_bak = new ComboPooledDataSource("bak");
	}

	public static DataSource getDataSource() {
		return ds;
	}
	
	public static DataSource getBakDataSource() {
		return ds_bak;
	}

	public static Connection getConnection() {
		try {
			Connection conn = tl.get();
			if (conn == null) {
				conn = ds.getConnection();
				conn.setAutoCommit(false);
			}
			tl.set(conn);
			return conn;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void startTransaction() {
		try {
			Connection conn = getConnection();
			conn.setAutoCommit(false);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void commitTransaction() {
		try {
			Connection conn = getConnection();
			if (conn != null) {
				conn.commit();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void closeConnection() {
		try {
			Connection conn = getConnection();
			if (conn != null) {
				conn.close();
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			tl.remove();
		}
	}
}
