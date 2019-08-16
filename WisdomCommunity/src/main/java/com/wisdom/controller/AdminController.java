package com.wisdom.controller;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.wisdom.model.Admin;
import com.wisdom.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AdminService adminService;

	@PostMapping("/register")
	@ResponseBody
	public Map<String, Object> register(Admin admin, MultipartFile file) {
		return adminService.register(admin, file);
	}

	@RequestMapping("/login")
	public Map<String, Object> login(Admin admin, HttpSession session) {
		return adminService.login(admin, session);
	}

	@RequestMapping("/getAdminInfo")
	@ResponseBody
	public Admin getAdminSession(HttpSession session) {
		System.out.println(session.getAttribute("admin"));
		return (Admin) session.getAttribute("admin");
	}

	@RequestMapping("/clear")
	public void clearSession(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			cookie.setMaxAge(0);
		}
	}

	@PostMapping("/edit")
	@ResponseBody
	public Map<String, Object> edit(Admin admin) {
		return adminService.update(admin);
	}

	@PostMapping("/delete")
	@ResponseBody
	public Map<String, Object> delete(Integer id) {
		return adminService.delete(id);
	}

}
