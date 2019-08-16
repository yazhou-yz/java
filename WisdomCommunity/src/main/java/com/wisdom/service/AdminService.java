package com.wisdom.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.wisdom.model.Admin;

public interface AdminService {
	Map<String, Object> register(Admin admin, MultipartFile file);

	Map<String, Object> login(Admin admin, HttpSession session);

	Map<String, Object> update(Admin admin);

	Map<String, Object> delete(Integer id);
}
