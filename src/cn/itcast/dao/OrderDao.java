package cn.itcast.dao;

import java.util.List;

import cn.itcast.domain.Order;
import cn.itcast.domain.User;

public interface OrderDao {

	public abstract void add(Order o);

	public abstract Order find(String id);

	public abstract List getAll(boolean state);
	
	public void update(Order o,boolean state);
	
	public List<Order> getAllByUser(User user);

}