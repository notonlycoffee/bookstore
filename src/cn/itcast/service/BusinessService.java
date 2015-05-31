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
	 * �����йصķ���
	 * 
	 *******************************/

	public abstract void addCategory(Category c);

	public abstract Category findCategory(String id);

	public abstract List<Category> getAllCategory();

	/********************************
	 * 
	 * ͼ���йصķ���
	 * 
	 *******************************/

	public abstract void addBook(Book book);

	public abstract Book findBook(String id);

	public abstract PageBean bookPageQuery(QueryInfo info);
	
	public List<Book> getAllBook();

	/********************************
	 * 
	 * �û��йصķ���
	 * 
	 *******************************/

	public abstract void addUser(User u);

	public abstract User findUser(String id);

	public abstract User findUser(String username, String password);

	/********************************
	 * 
	 * �����йصķ���
	 * 
	 *******************************/

	public abstract void saveOrder(Cart cart, User user);

	public abstract Order findOrder(String id);

	public abstract List<Order> getOrderByState(boolean state);
	
	public void updateOrder(Order o , boolean state);
	
	public List<Order> getOrderByUser(User user);

	
	/********************************
	 * 
	 * ���ݿⱸ���йصķ���
	 * 
	 *******************************/
	
	public void addDbbak(Dbbak bak);
	
	public List<Dbbak> getAllDbbak();
	
	public Dbbak findDbbak(String id);
	
}