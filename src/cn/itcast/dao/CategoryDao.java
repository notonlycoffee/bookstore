package cn.itcast.dao;

import java.util.List;

import cn.itcast.domain.Category;

public interface CategoryDao {

	public abstract void add(Category c);

	public abstract Category find(String id);

	public abstract List<Category> getAll();

}