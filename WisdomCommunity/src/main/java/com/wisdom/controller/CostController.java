package com.wisdom.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wisdom.service.CostService;

@Controller
@RequestMapping("/cost")
@ResponseBody
public class CostController {
	@Autowired
	private CostService costService;
	
	//添加
	@RequestMapping("/add")
	public Map<String, Object> add(Double costProperty, String costStatus, String costOid,String costHid) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式  
		String costTime = dateFormat.format(new Date());// new Date()为获取当前系统时间 
		
		System.out.println(costProperty);
		
		System.out.println(costStatus);
		System.out.println(costOid);
		System.out.println(costHid);
		return costService.add(costProperty, costStatus, costOid,costHid,costTime);
	}
		
	//编辑
	@RequestMapping("/edit")
	public Map<String, Object> edit(String costHid, String field, String value) {
		return costService.edit(costHid, field, value);
	}
	
//	//编辑金额 
//	@RequestMapping("/editProperty")
//	public Map<String, Object> editProperty(String costHid,Double costProperty) {
//		return costService.editProperty(costHid,costProperty);
//	}
//	
//	//编辑缴费状态
//	@RequestMapping("/edit")
//	public Map<String, Object> editStatus(String costHid,String costStatus) {
//		return costService.editStatus(costHid,costStatus);
//	}
	
	//查询全表
	@RequestMapping("/search")
	public Map<String, Object> search(Integer page, Integer limit){
		return costService.search(page,limit);
	}
	
	//根据业主id查询缴费信息
	@RequestMapping("/searchByOwnerId")
	public Map<String, Object> searchByOwnerId(String costOid){
		System.out.println("costOid" + costOid );
		return costService.searchByOwnerId(costOid);
	}
	//根据门牌号查询缴费信息、业主编号
	@RequestMapping("/searchByHouseId")
	public Map<String, Object> searchByHouseId(String costHid){
//		System.out.println("----------------------");
//		Map<String, Object> map = costService.searchByHouseId(costHid);
//		System.out.println("...."+map);
//		for (int i = 0; i < map.size(); i++) {
//			System.out.println(map.get("cost_id"));
//			System.out.println(map.get("cost_propery"+map.get("cost_propery")));
//			System.out.println(map.get("cost_status"+map.get("cost_status")));
//			System.out.println(map.get("cost_oid"+map.get("cost_oid")));
//			System.out.println(map.get("cost_hid"+map.get("cost_hid")));
//			System.out.println(map.get("cost_time"+map.get("cost_time")));
//		}
		return costService.searchByHouseId(costHid);
	}
	//根据缴费状态查询业主信息
	@RequestMapping("/searchCostStatus")
	public Map<String, Object> searchCostStatus(String costStatus){
		System.out.println("hhhhhhhh"+costStatus);
		return costService.searchCostStatus(costStatus);
	}
		

}
