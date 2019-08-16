package com.wisdom.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wisdom.service.HouseService;

@Controller
@RequestMapping("/house")
@ResponseBody
public class HouseController {
	@Autowired
	private HouseService houseService;

	// 添加
	@RequestMapping("/add")
	public Map<String, Object> add(String houseId, Double houseArea, String houseOwner) {
		return houseService.add(houseId, houseArea, houseOwner);
	}

	@RequestMapping("/batchAdd")
	public Map<String, Object> batchAdd(Integer building, Integer uniterm, Integer floor, Integer num) {
		return houseService.batchAdd(building, uniterm, floor, num);
	}

	// 编辑
	@RequestMapping("/edit")
	public Map<String, Object> edit(String field, String value, String houseId) {
		return houseService.houseEdit(field, value, houseId);

	}

	// 查询
	@RequestMapping("/search")
	public Map<String, Object> seach(int page, int limit) {
		return houseService.search(page, limit);
	}

	// 根据门牌号查询
	@RequestMapping("/seachHouseOwner")
	public Map<String, Object> searchHouseIdForOwner(String houseId) {
		return houseService.searchHouseIdForOwner(houseId);
	}

	@RequestMapping("/searchOwnerHouse")
	// 根据用户名查询房屋信息
	public Map<String, Object> searchHouseOwnerForHouse(String houseOwner) {
		return houseService.searchHouseOwnerForHouse(houseOwner);
	}

//	//添加时id的重复校验
//	public List<Map<String, Object>> seachHouseId(String houseId){
//		
//		return null;
//	}

}
