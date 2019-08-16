package com.wisdom.dao;

import java.util.List;
import java.util.Map;

import com.wisdom.model.Staff;

public interface StaffDao {

	// 添加
	public boolean add(String staffName, String staffType, String staffPhone, String staffStatus);

	// 编辑
	public boolean edit(String field, String value, String staffId);

	// 查询
	public List<Map<String, Object>> search(String staffName, String staffType, String staffStatus, String staffPhone,
			Integer page, Integer limit);

	// 查询总条数
	public Integer searchAllCount();

	public List<Staff> refresh();

	public Boolean delete(List<String> dels);

	//// 根据工种查询
	public Map<String, Object> searchStaffByType(String staffStatus);
}
