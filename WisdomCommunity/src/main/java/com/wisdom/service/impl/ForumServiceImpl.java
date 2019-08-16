package com.wisdom.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wisdom.commons.FilesUpload;
import com.wisdom.dao.ForumDAO;
import com.wisdom.model.Invitation;
import com.wisdom.service.ForumService;
@Service
public class ForumServiceImpl implements ForumService{
	@Autowired
	private ForumDAO dao;
	@Override
	public Map<String, Object> AddForum(MultipartFile file, String title, String content,String id) {
		String visPath=null;
		if (file!=null) {
			visPath=FilesUpload.filePart(file);
		}
		Date date=new Date();
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String fordate=format.format(date);
		Map<String, Object> map=new HashMap<String, Object>();
		if (dao.AddForum(id, title, content, visPath, fordate)) {
			map.put("code", 0);
			map.put("msg", "发帖成功");
		}else {
			map.put("code", 1);
			map.put("msg", "服务器 异常");
		}
		return map;
	}
	@Override
	public Map<String, Object> search(Integer page) {
        Map<String, Object> map=new HashMap<String, Object>();
        List<Map<String, Object>> list=dao.searchforum((page-1)*5);
			map.put("code", 0);
			map.put("data", list);
		return map;
	}
	@Override
	public Map<String, Object> searchCount() {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("count", dao.getCount());
		map.put("data", dao.searchforum(0));
		return map;
	}
	@Override
	public Invitation searchInvitation(Integer forum_id) {
		// TODO Auto-generated method stub
		return dao.searchInvitation(forum_id);
	}
	@Override
	public Map<String, Object> addcomMent(String content, String owner_id, int invitation_id) {
		Date date=new Date();
		Map<String, Object> map=new HashMap<String, Object>();
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String comment_date=format.format(date);
		if (dao.addcomMent(content, owner_id, invitation_id, comment_date)) {
			map.put("code", 0);
			map.put("msg", "发帖成功");
		}else {
			map.put("code", 1);
			map.put("msg", "发帖失败");
		}
		return map;
	}
	@Override
	public Map<String, Object> searchAllCount() {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("code", 0);
		map.put("data", dao.searchAllCount());
		return map;
	}
	
}
