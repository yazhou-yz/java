package com.wisdom.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wisdom.service.RepairsService;

@Controller
@RequestMapping("/repairs")
@ResponseBody
public class RepairsController {
	
	@Autowired
	private RepairsService repairsService;
	//添加
	@RequestMapping("/add")
	public Map<String, Object> add(String repairsOid,String repairsType,String repairsNeed ) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式  
		String repairsTime = dateFormat.format(new Date());// new Date()为获取当前系统时间 
		return repairsService.add(repairsOid, repairsType, repairsNeed, repairsTime);
		
	}
	//用户编辑(可以编辑报修类型、报修描述)
	@RequestMapping("/editOwner")
	public Map<String, Object> editOwner(String repairsOid,String repairsType,String repairsNeed) {
	    return repairsService.editOwner(repairsOid, repairsType, repairsNeed);
	}
	//管理员编辑(可以编辑报修状态)
	@RequestMapping("/editStaff")
	public Map<String, Object> editStaff(String field,String value,String repairsId) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式  
		String repairsTime = dateFormat.format(new Date());// new Date()为获取当前系统时间 
		String repairsTrace = repairsTime+field+value;
		return repairsService.editStaff(field,value,repairsId,repairsTrace); 
	}
	
	//查询全表
	@RequestMapping("/search")
	public Map<String, Object> search(Integer page,Integer limit){
		return repairsService.search(page,limit);
	}
	//通过用户手机号id查询报修的类型、报修的状态
	@RequestMapping("/searchRepairsForOid")
	public Map<String, Object> searchRepairsForOid(String repairsOid){
		return repairsService.searchRepairsForOid(repairsOid);
	}
	//根据报修类型查询
	@RequestMapping("/searchTypeForRepairs")
	public Map<String, Object> searchTypeForRepairs(String repairsType){
		return repairsService.searchTypeForRepairs(repairsType);
	}
	//根据报修状态查询
	@RequestMapping("/searchStateForRepairs")
	public Map<String, Object> searchStateForRepairs(String repairsState){
		return repairsService.searchStateForRepairs(repairsState);
	}

}


