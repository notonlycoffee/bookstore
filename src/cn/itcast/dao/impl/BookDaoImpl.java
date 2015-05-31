package cn.itcast.dao.impl;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.dao.BookDao;
import cn.itcast.domain.Book;
import cn.itcast.domain.QueryResult;
import cn.itcast.utils.JdbcUtils;

public class BookDaoImpl implements BookDao {

	/* (non-Javadoc)
	 * @see cn.itcast.dao.impl.BookDao#add(cn.itcast.domain.Book)
	 */
	@Override
	public void add(Book book) {
		try {
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "insert into book(id,name,price,author,image,description,category_id)  values(?,?,?,?,?,?,?)";
			Object [] params = {book.getId(), book.getName(), book.getPrice(), book.getAuthor(), book.getImage(), book.getDescription(), book.getCategory().getId()};
			runner.update(conn, sql, params);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	/* (non-Javadoc)
	 * @see cn.itcast.dao.impl.BookDao#find(java.lang.String)
	 */
	@Override
	public Book find(String id) {
		try {
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from book where id=?";
			return (Book) runner.query(conn, sql, id, new BeanHandler(Book.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	//�鱾̫����.����ֱ����getAll,Ҫ���÷�ҳ��ѯ
	//String where =  "where category_id=?"
	/*
	 * �û���where������������÷������ط�������ķ�ҳ��
	 * ���û��where�������򷵻�������ķ�ҳ����
	 * 
	 * where�����ĸ�ʽ��String where =  "where category_id=?"
	 * 
	 */
	private List getpagedata(int startindex, int pagesize, String where, Object param) {
		try {
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			if(where == null || where.trim().equals("")) {
				String sql = "select * from book limit ?,?";
				Object params [] = {startindex, pagesize};
				return (List) runner.query(conn, sql, params, new BeanListHandler(Book.class));
			} else {
				String sql = "select * from book " + where + " limit ?,?";
				Object params [] = {param, startindex, pagesize};
				return (List) runner.query(conn, sql, params, new BeanListHandler(Book.class));
			}
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private int gettotalrecord(String where, Object param) {
		try {
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			if(where == null || where.trim().equals("")) {
				String sql = "select count(*) from book";
				return ((Long) runner.query(conn, sql, new ScalarHandler())).intValue();
			} else {
				String sql = "select count(*) from book " + where;
				return ((Long) runner.query(conn, sql, param, new ScalarHandler())).intValue();
			}
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see cn.itcast.dao.impl.BookDao#pageQuery(int, int, java.lang.String, java.lang.Object)
	 */
	@Override
	public QueryResult pageQuery(int startindex, int pagesize, String where, Object param) {
		
		List list = getpagedata(startindex, pagesize, where, param);
		int totalRecord = gettotalrecord(where, param);
		QueryResult qr = new QueryResult();
		qr.setList(list);
		qr.setTotalRecord(totalRecord);
		return qr;
	}
	
	public List<Book> getAll() {
		try {
			
			Connection conn = JdbcUtils.getConnection() ;
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from book";
			return (List<Book>) runner.query(conn, sql, new BeanListHandler(Book.class));
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
