package cn.itcast.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.dao.DbbakDao;
import cn.itcast.domain.Dbbak;
import cn.itcast.utils.JdbcUtils;

public class DbbakDaoImpl implements DbbakDao {

	/* (non-Javadoc)
	 * @see cn.itcast.dao.impl.DbbakDao#add(cn.itcast.domain.Dbbak)
	 */
	@Override
	public void add(Dbbak bak) {
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getBakDataSource());
			String sql = "insert into bak(id,filename,baktime,description) values(?,?,?,?)";
			Object params [] = {bak.getId(),bak.getFilename(),bak.getBaktime(),bak.getDescription()};
			runner.update(sql, params);
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see cn.itcast.dao.impl.DbbakDao#getAll()
	 */
	@Override
	public List<Dbbak> getAll() {
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getBakDataSource());
			String sql = "select * from bak order by baktime desc";
			return (List<Dbbak>) runner.query(sql, new BeanListHandler(Dbbak.class));
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see cn.itcast.dao.impl.DbbakDao#find(java.lang.String)
	 */
	@Override
	public Dbbak find(String id) {
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getBakDataSource());
			String sql = "select * from bak where id=?";
			return (Dbbak) runner.query(sql, id, new BeanHandler(Dbbak.class));
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
