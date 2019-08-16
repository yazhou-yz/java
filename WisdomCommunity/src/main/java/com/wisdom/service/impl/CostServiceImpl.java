package com.wisdom.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.github.pagehelper.PageHelper;
import com.wisdom.dao.CostDao;
import com.wisdom.service.CostService;


@Service
public class CostServiceImpl implements CostService{

	@Autowired
	private CostDao costDao;
	
	//添加
	@Override
	public Map<String, Object> add(Double costProperty, String costStatus, String costOid,String costHid, String costTime) {
		Map<String, Object> result = new HashMap<String, Object>();
//		if(costProperty == null || costProperty <= 0) {
//			result.put("code", 2);
//			result.put("msg", "费用有误");
//			return result;
//		}
//		if(costStatus == null || costStatus.equals("")){
//			costStatus = "不存在";
//		}
//		if(costOid == null || costOid.length() != 11 ) {
//			result.put("code", 2);
//			result.put("msg", "业主编号有误");
//			return result;
//		}
//		if(costHid == null) {
//			result.put("code", 2);
//			result.put("msg", "业主门牌号有误");
//			return result;
//		}
		boolean success = costDao.add(costProperty, costStatus, costOid, costHid,costTime);
		if(success) {
			result.put("code", 0);
			result.put("msg", "添加成功");
		}else {
			result.put("code", 1);
			result.put("msg", "添加失败");
		}
		return result;
	}
	
	

	

	//编辑
	@Override
	public Map<String, Object> edit(String costHid,String field, String value) {
		Map<String, Object>  result = new HashMap<>();
		Object val = value;
		System.out.println("-----------------" +field);
		if(field.equals("cost_property")){
			val = Double.parseDouble(value);
		}
		System.out.println(field);
		boolean success = costDao.edit(costHid, field, value);
		
		if(success) {
			result.put("code", 0);
			result.put("msg", "编辑成功");
		}else {
			result.put("code", 1);
			result.put("msg", "编辑失败");
		}
		
		return result;
	}
	
//	//编辑金额 
//	@Override
//	public Map<String, Object> editProperty(String costHid, Double costProperty) {
//		Map<String, Object> result = new HashMap<>();
//		
//		
//		boolean success = costDao.editProperty(costHid, costProperty);
//		if (success) {
//			result.put("code", 0);
//			result.put("msg", "修改成功");
//		} else {
//			result.put("code", 1);
//			result.put("msg", "修改失败");
//		}
//		return result;
//	}
//
//	//编辑缴费状态
//	@Override
//	public Map<String, Object> editStatus(String costHid, String costStatus) {
//		Map<String, Object> result = new HashMap<>();
//		
//		boolean success = costDao.editStatus(costHid, costStatus);
//		if (success) {
//			result.put("code", 0);
//			result.put("msg", "修改成功");
//		} else {
//			result.put("code", 1);
//			result.put("msg", "修改失败");
//		}
//		return null;
//	}

	//查询
	@Override
	public Map<String, Object> search(Integer page,Integer limit) {
		PageHelper.startPage(page,limit);
		List<Map<String, Object>> date = costDao.search();
		Map<String, Object> result = new HashMap<>();
		if(date.size() <=0 || date == null) {
			result.put("code", 1);
			result.put("msg", "查询失败");
			return result;
		}
			result.put("code", 0);
			result.put("msg", "查询成功");
			result.put("data", date);
			result.put("count", costDao.searchAllCount());
			return result;
		}

	//根据业主id查询缴费状态和业主编号缴费金额
	@Override
	public Map<String, Object> searchByOwnerId(String costOid) {
		Map<String, Object> result = new HashMap<>();
		
		List<Map<String, Object>> data = costDao.searchByOwnerId(costOid);
		if(null == data || data.size() <= 0) {
			result.put("code", 1);
			result.put("msg", "查询失败");
		}
		result.put("code", 0);
		result.put("msg", "查询成功");
		result.put("data", data);
		return result;
	}

	//根据门牌号查询
	@Override
	public Map<String, Object> searchByHouseId(String costHid) {
		Map<String, Object> result = new HashMap<>();
		if(null == costHid) {
			result.put("code", 2);
			result.put("msg", "查询失败");
		}
		List<Map<String, Object>> data = costDao.searchByHouseId(costHid);
		
		if(null == data || data.size() <= 0) {
			result.put("code", 1);
			result.put("msg", "查询失败");
		}
		result.put("code", 0);
		result.put("msg", "查询成功");
		result.put("data", data);
		return result;
	}

	//根据缴费状态查询业主信息
	@Override
	public Map<String, Object> searchCostStatus(String costStatus) {
		
		Map<String, Object> result = new HashMap<>();
		List<Map<String, Object>> data = costDao.searchCostStatus(costStatus);
		   if(null==data || data.size() <= 0) {
		   result.put("code", 1);
		   result.put("msg", "查询结果为空");
		   return result;
		}
//		  for (Map<String, Object> map : data) {
//			System.out.println("cost_id"+map.get("cost_id"));
//			System.out.println("cost_property"+map.get("cost_property"));
//			System.out.println("cost_status"+map.get("cost_status"));
//			System.out.println("cost_oid"+map.get("cost_oid"));
//			System.out.println("cost_hid"+map.get("cost_hid"));
//			System.out.println("cost_time"+map.get("cost_time"));
//		}
		   result.put("code", 0);
		   result.put("msg", "查询成功");
		   result.put("data", data);
		
		return result;
	}





	


}
