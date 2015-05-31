package Junit.test;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import cn.itcast.domain.Book;
import cn.itcast.domain.Cart;
import cn.itcast.domain.CartItem;
import cn.itcast.domain.Category;
import cn.itcast.domain.QueryInfo;
import cn.itcast.domain.User;
import cn.itcast.service.impl.BusinessServiceImpl;
import cn.itcast.utils.JdbcUtils;

public class BusinessServiceTest {
	
	private BusinessServiceImpl service = new BusinessServiceImpl(); 
	
	@Test
	public void testAddCategory() {
		
		Category c = new Category();
		c.setId("1");
		c.setName("aaa");
		c.setDescription("bbbb");
		
		service.addCategory(c);
		JdbcUtils.commitTransaction();
		
	}
	
	@Test
	public void testFindCategory() {
		service.findCategory("1");
		JdbcUtils.commitTransaction();
	}
	
	@Test
	public void testGetAllCategory() {
		service.getAllCategory();
		JdbcUtils.commitTransaction();
	}
	
	@Test
	public void testAddBook() {
		Category c = new Category();
		c.setId("1");
		Book book = new Book();
		book.setAuthor("老张");
		book.setCategory(c);
		book.setDescription("aaa");
		book.setId("1");
		book.setImage("bbb");
		book.setName("java开发");
		book.setPrice(123);
		service.addBook(book);
		JdbcUtils.commitTransaction();
	}
	
	@Test
	public void testFindBook() {
		service.findBook("1");
		
		JdbcUtils.commitTransaction();
	}
	
	@Test
	public void testBookPageQuery() {
		QueryInfo info = new QueryInfo();
		info.setCurrentPage(1);
		info.setPageSize(3);
		info.setQueryName("category_id");
		info.setQueryValue("1");
		service.bookPageQuery(info);
		JdbcUtils.commitTransaction();
	}
	
	@Test
	public void testAddUser() {
		User u = new User();
		u.setAddress("aaa");
		u.setCellphone("132234");
		u.setEmail("aa@qq.com");
		u.setId("1");
		u.setPassword("aaaa");
		u.setPhone("1341234");
		u.setUsername("bbb");
		service.addUser(u);
		JdbcUtils.commitTransaction();
	}
	
	@Test
	public void testFindUser() {
		service.findUser("1");
		JdbcUtils.commitTransaction();
		
	}
	
	@Test
	public void testFindUserByNamePassword() {
		service.findUser("bbb", "aaaa");
		JdbcUtils.commitTransaction();
	}
	
	@Test
	public void testSaveOrder() {
		
		User user = service.findUser("1");
		
		Book b1 = service.findBook("1");
		
		Cart c = new Cart();
		
		c.addBook(b1);
		
		service.saveOrder(c, user);
		JdbcUtils.commitTransaction();
	}
	
	@Test
	public void testFindOrder() {
		service.findOrder("2eb42db2-ffa4-43e6-88b0-fa3f44998175");
	}
	
	
	@Test
	public void testGetOrderByState() {
		service.getOrderByState(false);
	}
	
}
