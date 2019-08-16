package com.wisdom.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.wisdom.dao.RepairsDao;
import com.wisdom.service.RepairsService;

@Service
public class RepairsServiceImpl implements RepairsService {
	@Autowired
	private RepairsDao repairsDao;
	//添加
	@Override
	public Map<String, Object> add(String repairsOid,String repairsType,String repairsNeed,String repairsTime) {
		Map<String, Object> result = new HashMap<String, Object>();
		String repairsState = "报修";
		if(null==repairsOid || null== repairsType || null==repairsNeed) {
			result.put("code", 2);
			result.put("msg", "用户id、报修类型和报修描述不能为空");
			return result;
		}
		if(repairsOid.trim().equals(" ")||repairsType.trim().equals(" ")||repairsNeed.trim().equals(" ")) {
			result.put("code", 2);
			result.put("msg", "用户id、报修类型和报修描述输入不是一个文本");
			return result;
		}
		String repairsTrace = repairsTime + repairsState;
		boolean success = repairsDao.add(repairsType, repairsNeed, repairsState, repairsTime, repairsOid, repairsTrace);
		if(success) {
			result.put("code", 0);
			result.put("msg", "添加成功");
		}else {
			result.put("code", 1);
			result.put("msg", "添加失败");
		}
		return result;
	}
    //用户编辑(可以编辑报修类型、报修描述)
	@Override
	public Map<String, Object> editOwner(String repairsOid,String repairsType,String repairsNeed) {
		Map<String, Object>  result = new HashMap<>();
		if(null == repairsOid || Integer.parseInt(repairsOid) <= 0 ) {
			result.put("code", 2);
			result.put("msg", "未获取到当前需要编辑的信息");
			return result;
		}else if(null==repairsType||repairsType.trim().equals(" ")) {
			result.put("code", 2);
			result.put("msg", "抱歉,需要更改的报修类型超出服务范围");
			return result;
		}else if(null==repairsNeed || repairsNeed.trim().equals(" ")){
			result.put("code", 2);
			result.put("msg", "抱歉报修描述不能为空");
			return result;
		}else {
			boolean success = repairsDao.editOwner(repairsOid, repairsType, repairsNeed);
			if(success) {
				result.put("code", 0);
				result.put("msg", "业主编辑报修信息成功");
			}else {
				result.put("code", 1);
				result.put("msg", "业主编辑报修信息失败");
			}
			return result;
			
		}
	}
	
	//管理员编辑(可以编辑报修状态)
	@Override
	public Map<String, Object> editStaff(String field,String value,String repairsId, String repairsTrace){
		Map<String, Object>  result = new HashMap<>();
		if(null == repairsId || Integer.parseInt(repairsId) <= 0) {
			result.put("code", 2);
			result.put("msg", "未获取到当前需要编辑的信息");
			return result;
		}else {
			boolean success = repairsDao.editStaff(field,value,repairsId,repairsTrace);
			if(success) {
				result.put("code", 0);
				result.put("msg", "管理员更改维修状态成功");
			}else {
				result.put("code", 1);
				result.put("msg", "管理员更改维修状态失败");
			}
			return result;
		}
	}
	
    //查询
	@Override
	public Map<String, Object> search(Integer page,Integer limit) {
		PageHelper.startPage(page, limit);
		List<Map<String, Object>> data = repairsDao.search(page,limit);
		Map<String, Object> result = new HashMap<>();
		if(data.size() >0 && data != null) {
			result.put("code", 0);
			result.put("msg", "查询成功");
			result.put("count", repairsDao.searchCount());
			result.put("data", data);
			return result;
		}
		result.put("code", 1);
		result.put("msg", "没有找到相关结果");
		return result;
	}
	//通过用户手机号id查询报修的类型、报修的状态
	@Override
	public Map<String, Object> searchRepairsForOid(String repairsOid) {
		Map<String, Object> result = new HashMap<>();
		if(null==repairsOid || repairsOid.trim().equals("")) {
			System.out.println("repairsOid="+repairsOid);
			result.put("code", 2);
			result.put("msg", "查询条件不符合要求");
			return result;
		}
		List<Map<String, Object>> data = repairsDao.searchRepairsForOid(repairsOid);
		if(null==data || data.size() <= 0) {
			result.put("code", 1);
			result.put("msg", "查询结果为空");
			return result;
		}
		result.put("code", 0);
		result.put("msg", "查询成功");
		result.put("data", data);
		return result;
	}
	//根据报修状态查询
	@Override
	public Map<String, Object> searchStateForRepairs(String repairsState) {
		Map<String, Object> result = new HashMap<>();
		if(null == repairsState || !(repairsState.equals("报修"))||(!(repairsState.equals("已受理")))||(!(repairsState.equals("已维修"))) ||(!(repairsState.equals("与业主电话沟通中")))) {
			result.put("code", 2);
			result.put("msg", "查询状态不符");
			return result;
		}
		List<Map<String, Object>> data = repairsDao.searchStateForRepairs(repairsState);
		if(null==data || data.size() <= 0) {
			result.put("code", 1);
			result.put("msg", "查询结果为空");
			return result;
		}
		result.put("code", 0);
		result.put("msg", "查询成功");
		result.put("data", data);
		return result;
	}
	//根据报修类型查询
	public Map<String, Object> searchTypeForRepairs(String repairsType){
		Map<String, Object> result = new HashMap<>();
		if(null == repairsType ||repairsType.trim().equals("")) {
			result.put("code", 2);
			result.put("msg", "查询状态不符");
			return result;
		}else {
		 	List<Map<String, Object>> data = repairsDao.searchTypeForRepairs(repairsType);
		 	if(null==data || data.size()<=0) {
		 		result.put("code", 1);
				result.put("msg", "查询结果为空");
		 	}else {
		 		result.put("code", 0);
				result.put("msg", "查询成功");
				result.put("data", data);
			}
		 	return result;
		}
	}
}
