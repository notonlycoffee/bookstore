package Junit.test;

import org.junit.Test;

import cn.itcast.dao.BookDao;
import cn.itcast.dao.impl.BookDaoImpl;
import cn.itcast.domain.Book;
import cn.itcast.domain.Category;
import cn.itcast.utils.JdbcUtils;

public class BookDaoTest {

	@Test
	public void testpagequery() {

		BookDao dao = new BookDaoImpl();
		dao.pageQuery(0, 3, "", 1);

	}

	@Test
	public void addBook() {
		Category g = new Category();
		g.setId("1");
		Book b = new Book();
		b.setAuthor("aaa");
		b.setCategory(g);
		b.setDescription("asf");
		b.setId("a");
		b.setImage("222");
		b.setName("jdk");
		b.setPrice(234);
		BookDao dao = new BookDaoImpl();
		dao.add(b);
		JdbcUtils.commitTransaction();
	}

	@Test
	public void testfind() {
		BookDao dao = new BookDaoImpl();
		dao.find("1");
		System.out.println();
	}


}
