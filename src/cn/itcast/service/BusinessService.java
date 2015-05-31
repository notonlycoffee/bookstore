package cn.itcast.service;

import java.util.List;

import cn.itcast.domain.Book;
import cn.itcast.domain.Cart;
import cn.itcast.domain.Category;
import cn.itcast.domain.Dbbak;
import cn.itcast.domain.Order;
import cn.itcast.domain.PageBean;
import cn.itcast.domain.QueryInfo;
import cn.itcast.domain.User;

public interface BusinessService {

	/********************************
	 * 
	 * 分类有关的服务
	 * 
	 *******************************/

	public abstract void addCategory(Category c);

	public abstract Category findCategory(String id);

	public abstract List<Category> getAllCategory();

	/********************************
	 * 
	 * 图书有关的服务
	 * 
	 *******************************/

	public abstract void addBook(Book book);

	public abstract Book findBook(String id);

	public abstract PageBean bookPageQuery(QueryInfo info);
	
	public List<Book> getAllBook();

	/********************************
	 * 
	 * 用户有关的服务
	 * 
	 *******************************/

	public abstract void addUser(User u);

	public abstract User findUser(String id);

	public abstract User findUser(String username, String password);

	/********************************
	 * 
	 * 订单有关的服务
	 * 
	 *******************************/

	public abstract void saveOrder(Cart cart, User user);

	public abstract Order findOrder(String id);

	public abstract List<Order> getOrderByState(boolean state);
	
	public void updateOrder(Order o , boolean state);
	
	public List<Order> getOrderByUser(User user);

	
	/********************************
	 * 
	 * 数据库备份有关的服务
	 * 
	 *******************************/
	
	public void addDbbak(Dbbak bak);
	
	public List<Dbbak> getAllDbbak();
	
	public Dbbak findDbbak(String id);
	
}