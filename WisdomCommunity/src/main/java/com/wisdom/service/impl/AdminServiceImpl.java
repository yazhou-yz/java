package com.wisdom.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wisdom.commons.FilesUpload;
import com.wisdom.commons.FilesUploadz;
import com.wisdom.dao.AdminDao;
import com.wisdom.model.Admin;
import com.wisdom.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminDao adminDao;
	private Map<String, Object> json = new HashMap<String, Object>();

	@Override
	public Map<String, Object> register(Admin admin, MultipartFile file) {
		if (admin.getAdminName() != null && admin.getAdminPassword() != null) {
			String visitPath = null;
			if (file != null) {
				visitPath = FilesUploadz.filePart(admin.getAdminName(), file);
			}
			if (adminDao.register(admin.getAdminName(), admin.getAdminPassword(), visitPath)) {
				json.put("code", 0);
				json.put("msg", "注册成功");
			} else {
				json.put("code", 1);
				json.put("msg", "注册失败");
			}
		}
		return json;
	}

	@Override
	public Map<String, Object> login(Admin admin, HttpSession session) {
		Admin adminInfo = adminDao.login(admin.getAdminName(), admin.getAdminPassword());
		if (adminInfo != null) {
			adminInfo.setAdminPassword(null);
			json.put("code", 0);
			json.put("msg", "登录成功");
			json.put("data", adminInfo);
			session.setAttribute("admin", adminInfo);
		} else {
			json.put("code", 1);
			json.put("msg", "账号或密码有误");
		}
		return json;
	}

	@Override
	public Map<String, Object> update(Admin admin) {
		if (adminDao.update(admin)) {
			json.put("code", 0);
			json.put("msg", "修改成功");
		} else {
			json.put("code", 1);
			json.put("msg", "修改失败");
		}
		return json;
	}

	@Override
	public Map<String, Object> delete(Integer id) {
		if (adminDao.delete(id)) {
			json.put("code", 0);
			json.put("msg", "删除成功");
		} else {
			json.put("code", 1);
			json.put("msg", "删除失败");
		}
		return json;
	}

}
