package cn.itcast.dao;

import java.util.List;

import cn.itcast.domain.Dbbak;

public interface DbbakDao {

	public abstract void add(Dbbak bak);

	public abstract List<Dbbak> getAll();

	public abstract Dbbak find(String id);

}