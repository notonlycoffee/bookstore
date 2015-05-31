package cn.itcast.dao.impl;

import java.sql.Connection;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.itcast.dao.UserDao;
import cn.itcast.domain.User;
import cn.itcast.utils.JdbcUtils;

public class UserDaoImpl implements UserDao {
	
	/* (non-Javadoc)
	 * @see cn.itcast.dao.impl.UserDao#add(cn.itcast.domain.User)
	 */
	@Override
	public void add(User u) {
		try {
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "insert into user(id,username,password,phone,cellphone,email,address) values(?,?,?,?,?,?,?)";
			Object params [] = {u.getId(),u.getUsername(),u.getPassword(),u.getPhone(),u.getCellphone(),u.getEmail(),u.getAddress()};
			runner.update(conn, sql, params);
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see cn.itcast.dao.impl.UserDao#find(java.lang.String)
	 */
	@Override
	public User find(String id) {
		try {
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from user where id=?";
			return (User) runner.query(conn, sql, id, new BeanHandler(User.class));
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see cn.itcast.dao.impl.UserDao#find(java.lang.String, java.lang.String)
	 */
	@Override
	public User find(String username, String password) {
		try {
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from user where username=? and password=?";
			Object params [] = {username, password};
			return (User) runner.query(conn, sql, params, new BeanHandler(User.class));
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
