package com.wisdom.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wisdom.model.Owner;
import com.wisdom.service.OwnerService;

@RestController
@RequestMapping("/owner")
public class OwnerController {
	@Autowired
	private OwnerService ownerService;

	@RequestMapping("/register")
	public Map<String, Object> register(Owner owner, MultipartFile file) {
		return ownerService.register(owner, file);
	}

	@RequestMapping("/search")
	public Map<String, Object> search(Integer page, Integer limit) {
		return ownerService.search(page, limit);
	}

	@RequestMapping("/searchByName")
	public Map<String, Object> searchByName(String ownerName) {
//		return ownerService.search(page, limit);
		return null;
	}

	@RequestMapping("/login")
	public Map<String, Object> login(Owner owner, HttpSession session) {
		return ownerService.login(owner, session);
	}

	@RequestMapping("/getInfo")
	public Owner getSession(HttpSession session) {
		return (Owner) session.getAttribute("owner");
	}

	@RequestMapping("/logout")
	public Object delSession(HttpSession session) {
		return ownerService.logout(session);
	}

	@RequestMapping("/update")
	public Map<String, Object> update(String oldPwd, String newPwd, String ownerId) {
		return ownerService.update(oldPwd, newPwd, ownerId);
	}

	@RequestMapping("/delete")
	public Map<String, Object> delete(String id) {
		return ownerService.delete(id);
	}
}
