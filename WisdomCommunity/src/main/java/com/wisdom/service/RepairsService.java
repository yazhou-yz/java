package com.wisdom.service;

import java.util.Map;

public interface RepairsService {
	//添加
	public Map<String, Object> add(String repairsOid,String repairsType,String repairsNeed,String repairsTime);	
	//用户编辑(可以编辑报修类型、报修描述)
	public Map<String, Object> editOwner(String repairsOid,String repairsType,String repairsNeed);
	//管理员编辑(可以编辑报修状态)
	public Map<String, Object> editStaff(String field,String value,String repairsId, String repairsTrace);
	//查询
	public Map<String, Object> search(Integer page,Integer limit);
	//通过用户手机号id查询报修的类型、报修的状态
	public Map<String, Object> searchRepairsForOid(String repairsOid);
	//根据报修类型查询
	public Map<String, Object> searchTypeForRepairs(String repairsType);
	//根据报修状态查询
	public Map<String, Object> searchStateForRepairs(String repairsState);
}
