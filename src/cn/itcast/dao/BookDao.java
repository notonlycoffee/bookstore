package cn.itcast.dao;

import java.util.List;

import cn.itcast.domain.Book;
import cn.itcast.domain.QueryResult;

public interface BookDao {

	public abstract void add(Book book);

	public abstract Book find(String id);

	public abstract QueryResult pageQuery(int startindex, int pagesize,
			String where, Object param);

	public List<Book> getAll();
}