package com.wisdom.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.wisdom.model.Owner;

public interface OwnerService {
	Map<String, Object> register(Owner owner, MultipartFile file);

	Map<String, Object> login(Owner owner, HttpSession session);

	Map<String, Object> update(String oldPwd, String newPwd, String ownerId);

	Map<String, Object> delete(String id);

	Map<String, Object> logout(HttpSession session);

	Map<String, Object> search(Integer page, Integer limit);

	Map<String, Object> searchByName(String ownerName);
}
