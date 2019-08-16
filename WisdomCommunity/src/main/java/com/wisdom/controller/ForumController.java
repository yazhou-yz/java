package com.wisdom.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.wisdom.service.ForumService;

@Controller
@RequestMapping("/forum")
public class ForumController {
	@Autowired
	private ForumService service;
	@RequestMapping("/search")
	@ResponseBody
	public Map<String, Object> search(Integer page){
		return service.search(page);
	}
	@RequestMapping("/searchcount")
	@ResponseBody
	public Map<String, Object> searchCount(){
		return service.searchCount();
	}
	@RequestMapping("/add")
	@ResponseBody
	public Map<String, Object> AddForum(MultipartFile file,String title,String content,String  ownerId){
		System.err.println(file);
		System.err.println(title+content);
		System.err.println(ownerId);
		return service.AddForum(file, title, content, ownerId);
	}
	@RequestMapping("/searchInvitation")
	public String searchInvitation(Integer forum_id,HttpServletRequest req){
		req.getSession().setAttribute("Invitation", service.searchInvitation(forum_id));
		return "tiezi";
	}
	@RequestMapping("/getInvitation")
	@ResponseBody
	public Object getInvitation(HttpServletRequest req) {
		return req.getSession().getAttribute("Invitation");
	}
	@RequestMapping("/addcomment")
	@ResponseBody
	public Map<String, Object> addcomMent(String content,String owner_id,int invitation_id) {
		System.err.println(content+owner_id+invitation_id);
		return service.addcomMent(content, owner_id, invitation_id);
	}
	@RequestMapping("/searchAllCount")
	@ResponseBody
	public Map<String, Object> searchAllCount(){
		return service.searchAllCount();
	}
}
