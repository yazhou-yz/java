package com.wisdom.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.wisdom.model.Invitation;

public interface ForumService {
	public Map<String, Object> addcomMent(String content,String owner_id,int invitation_id);
	public Invitation searchInvitation(Integer forum_id);
	public Map<String, Object> searchCount();
	public Map<String, Object> search(Integer page);
	public Map<String, Object> searchAllCount();
	public Map<String, Object> AddForum(MultipartFile file,String title,String content,String id);
}
