package com.wisdom.service;

import java.util.Map;

public interface StaffService {

	// 添加
	public Map<String, Object> add(String staffName, String staffType, String staffPhone, String staffStatus);

	// 编辑
	public Map<String, Object> edit(String field, String value, String staffId);

	// 查找
	public Map<String, Object> search(String staffName, String staffType, String staffStatus, String staffPhone,
			Integer page, Integer limit);

	public Map<String, Object> refresh(Integer page, Integer limit);

	public Map<String, Object> delete(String ids);

	// 根据工种查询
	public Map<String, Object> searchStaffByType(String staffStatus);

}
