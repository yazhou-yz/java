package com.wisdom.dao;

import java.util.List;
import java.util.Map;

import com.wisdom.model.Admin;

public interface AdminDao {
	Boolean register(String adminName, String adminPassword, String visitPath);

	Admin login(String adminName, String adminPassword);

	List<Admin> selectByMap(Map<String, Object> map);

	Boolean update(Admin admin);

	Boolean delete(Integer id);
}
