package Junit.test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;

import org.junit.Test;

import cn.itcast.dao.OrderDao;
import cn.itcast.dao.impl.OrderDaoImpl;
import cn.itcast.domain.Order;
import cn.itcast.domain.User;
import cn.itcast.utils.JdbcUtils;

public class OrderDaoTest {

	@Test
	public void testquery() {
		
		OrderDao dao = new OrderDaoImpl();
		dao.find("2");
		System.out.println();
		
	}
	
	@Test
	public void testadd() {
		Order o = new Order();
		User u = new User();
		u.setId("1");
		OrderDao dao = new OrderDaoImpl();
		o.setId("sdf");
		o.setOrderitems(new HashSet());
		
		Date date = new Date();
		Timestamp timeStamp = new Timestamp(date.getTime());
		
		o.setOrdertime(timeStamp);
		o.setPrice(234);
		o.setUser(u);
		
		dao.add(o);
		JdbcUtils.commitTransaction();
		
	}
}
