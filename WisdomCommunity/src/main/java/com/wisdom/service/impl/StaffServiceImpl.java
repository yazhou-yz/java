package com.wisdom.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.wisdom.dao.StaffDao;
import com.wisdom.service.StaffService;

@Service
@ResponseBody
public class StaffServiceImpl implements StaffService {

	@Autowired
	private StaffDao staffDao;

	// 添加
	@Override
	public Map<String, Object> add(String staffName, String staffType, String staffPhone, String staffStatus) {
		boolean success = staffDao.add(staffName, staffType, staffPhone, staffStatus);
		Map<String, Object> result = new HashMap<String, Object>();
		if (success) {
			result.put("code", 0);
			result.put("msg", "添加成功");
		} else {
			result.put("code", 1);
			result.put("msg", "添加失败");
		}
		return result;
	}

	// 编辑
	@Override
	public Map<String, Object> edit(String field, String value, String staffId) {
		boolean success = staffDao.edit(field, value, staffId);
		Map<String, Object> result = new HashMap<>();
		if (success) {
			result.put("code", 0);
			result.put("msg", "编辑成功");
		} else {
			result.put("code", 1);
			result.put("msg", "编辑失败");
		}

		return result;
	}

	// 查询
	@Override
	public Map<String, Object> search(String staffName, String staffType, String staffStatus, String staffPhone,
			Integer page, Integer limit) {
		PageHelper.startPage(page, limit);
		List<Map<String, Object>> data = staffDao.search(staffName, staffType, staffStatus, staffPhone, page, limit);
		Map<String, Object> result = new HashMap<>();
		result.put("code", 0);
		if (data == null || data.size() == 0) {
			result.put("count", 0);
		} else {
			result.put("msg", "查询成功");
			result.put("count", staffDao.searchAllCount());
			result.put("data", data);
		}
		return result;
	}

	// 根据工种查询
	@Override
	public Map<String, Object> searchStaffByType(String staffType) {
		return null;
	}

	@Override
	public Map<String, Object> refresh(Integer page, Integer limit) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", 0);
		result.put("msg", "查询成功");
		result.put("count", staffDao.searchAllCount());
		PageHelper.startPage(page, limit);
		result.put("data", staffDao.refresh());
		return result;
	}

	@Override
	public Map<String, Object> delete(String ids) {
		List<String> dels = Arrays.asList(ids.split(","));
		Map<String, Object> result = new HashMap<String, Object>();
		if (staffDao.delete(dels)) {
			result.put("code", 0);
			result.put("msg", "删除成功");
		} else {
			result.put("code", 1);
			result.put("msg", "删除失败");
		}
		return result;
	}

}
