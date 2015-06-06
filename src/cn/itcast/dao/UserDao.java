package cn.itcast.dao;

import java.util.List;

import cn.itcast.domain.Privilege;
import cn.itcast.domain.User;

public interface UserDao {

	public abstract void add(User u);

	public abstract User find(String id);

	public abstract User find(String username, String password);
	
	public List<Privilege> getAllPrivilege(User user);

}