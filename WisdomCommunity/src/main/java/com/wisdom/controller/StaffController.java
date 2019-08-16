package com.wisdom.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wisdom.service.StaffService;

@Controller
@RequestMapping("/staff")
@ResponseBody
public class StaffController {

	@Autowired
	private StaffService staffService;

	// 增加
	@RequestMapping("/add")
	public Map<String, Object> add(String staffName, String staffType, String staffPhone, String staffStatus) {
		return staffService.add(staffName, staffType, staffPhone, staffStatus);
	}

	// 编辑
	@RequestMapping("/edit")
	public Map<String, Object> edit(String field, String value, String staffId) {
		return staffService.edit(field, value, staffId);
	}

	// 查询
	@RequestMapping("/search")
	public Map<String, Object> search(String staffName, String staffType, String staffStatus, String staffPhone,
			Integer page, Integer limit) {
		return staffService.search(staffName, staffType, staffStatus, staffPhone, page, limit);
	}

	@RequestMapping("/refresh")
	public Map<String, Object> refresh(Integer page, Integer limit) {
		return staffService.refresh(page, limit);
	}

	@RequestMapping("/delete")
	public Map<String, Object> delete(String ids) {
		return staffService.delete(ids);
	}

	// 根据工种查询
	public Map<String, Object> searchStaffByType(String staffStatus) {
		return null;
	}

}
