package com.wisdom.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wisdom.service.InformService;

@Controller
@RequestMapping("/inform")
@ResponseBody
public class InformController {
	@Autowired
	private InformService informService;
	//添加
	@RequestMapping("/add")
	public Map<String, Object> add(String informTitle,String informMessage){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式  
		String informTime = dateFormat.format(new Date());// new Date()为获取当前系统时间 
		return informService.add(informTime, informTitle, informMessage);  
	}
	//编辑
	@RequestMapping("/edit")
	public Map<String, Object> edit(Integer informId, String informTitle,String informMessage){
		return informService.edit(informId, informTitle, informMessage);
	}
	//查看
	@RequestMapping("/search")
	public Map<String, Object> search(Integer page, Integer limit,Integer informId){
		System.out.println(informId);
		return informService.search(page, limit, informId);
	}
	//查询最新通知的表头
	@RequestMapping("/searchTitle")
	public Map<String, Object> searchTitle(){
		return informService.searchTitle();
	}

}
