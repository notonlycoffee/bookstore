package cn.itcast.dao.impl;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.dao.CategoryDao;
import cn.itcast.domain.Category;
import cn.itcast.utils.JdbcUtils;

public class CategoryDaoImpl implements CategoryDao {

	/* (non-Javadoc)
	 * @see cn.itcast.dao.impl.CategoryDao#add(cn.itcast.domain.Category)
	 */
	@Override
	public void add(Category c) {

		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			Connection conn = JdbcUtils.getConnection();
			String sql = "insert into category(id,name,description) values(?,?,?)";
			Object params[] = { c.getId(), c.getName(), c.getDescription() };
			runner.update(conn, sql, params);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	/* (non-Javadoc)
	 * @see cn.itcast.dao.impl.CategoryDao#find(java.lang.String)
	 */
	@Override
	public Category find(String id) {
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			Connection conn = JdbcUtils.getConnection();
			String sql = "select * from category where id=?";
			return (Category) runner.query(conn, sql, id, new BeanHandler(Category.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/* (non-Javadoc)
	 * @see cn.itcast.dao.impl.CategoryDao#getAll()
	 */
	@Override
	public List<Category> getAll() {
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			Connection conn = JdbcUtils.getConnection();
			String sql = "select * from category";
			return (List<Category>) runner.query(conn, sql,new BeanListHandler(Category.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
