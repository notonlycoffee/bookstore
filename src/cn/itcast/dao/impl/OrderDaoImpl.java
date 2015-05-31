package cn.itcast.dao.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Set;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.dao.OrderDao;
import cn.itcast.domain.Book;
import cn.itcast.domain.Order;
import cn.itcast.domain.OrderItem;
import cn.itcast.domain.User;
import cn.itcast.utils.JdbcUtils;

public class OrderDaoImpl implements OrderDao {

	/* (non-Javadoc)
	 * @see cn.itcast.dao.impl.OrderDao#add(cn.itcast.domain.Order)
	 */
	@Override
	public void add(Order o) {
		try {
			
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "insert into orders(id,ordertime,state,price,user_id) values(?,?,?,?,?)";
			Object [] params = {o.getId(),o.getOrdertime(),o.isState(),o.getPrice(),o.getUser().getId()};
			runner.update(conn, sql, params);
			
			Set<OrderItem> set = o.getOrderitems();
			for( OrderItem item : set) {
				sql = "insert into orderitem(id,quantity,price,book_id,order_id) values(?,?,?,?,?)";
				params = new Object [] {item.getId(),item.getQuantity(),item.getPrice(),item.getBook().getId(),o.getId()};
				runner.update(conn, sql, params);
			}
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see cn.itcast.dao.impl.OrderDao#find(java.lang.String)
	 */
	@Override
	public Order find(String id) {
		
		try {
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			
			String sql = "select * from orders where id=?";
			Order o = (Order) runner.query(conn, sql, id, new BeanHandler(Order.class));
			
			sql = "select * from orderitem where order_id=?";
			List<OrderItem> list = (List<OrderItem>) runner.query(conn, sql, id, new BeanListHandler(OrderItem.class));
			o.getOrderitems().addAll(list);
			
			for( OrderItem item : list) {
				sql = "select b.* from orderitem oi,book b where oi.id=? and oi.book_id=b.id";
				Book book = (Book) runner.query(conn, sql, item.getId(), new BeanHandler(Book.class));
				item.setBook(book);
			}
			
			sql = "select u.* from orders os,user u where os.id=? and os.user_id=u.id";
			User u = (User) runner.query(conn, sql, id, new BeanHandler(User.class));
			
			o.setUser(u);
			
			return o;
			
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
	/* (non-Javadoc)
	 * @see cn.itcast.dao.impl.OrderDao#getAll(boolean)
	 */
	@Override
	public List getAll(boolean state) {
		
		try {
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from orders where state=?";
			List<Order> list = (List) runner.query(conn, sql, state, new BeanListHandler(Order.class));
			
			for( Order o : list) {
				sql = "select u.* from orders os,user u where os.id=? and os.user_id=u.id";
				User u = (User) runner.query(conn, sql, o.getId(), new BeanHandler(User.class));
				o.setUser(u);
			}
			return list;
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void update(Order o,boolean state) {
		try {
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner =  new QueryRunner(JdbcUtils.getDataSource());
			String sql = "update orders set ordertime=?, state=?, price=?, user_id=? where id=?";
			Object params [] = {o.getOrdertime(), state, o.getPrice(), o.getUser().getId(), o.getId()};
			runner.update(conn, sql, params);
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Order> getAllByUser(User user) {
		try {
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner =new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from orders where user_id=?";
			List<Order> list = (List<Order>) runner.query(conn, sql, user.getId(), new BeanListHandler(Order.class));
			for(Order o : list) {
				o.setUser(user);
			}
			return list;
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
}
