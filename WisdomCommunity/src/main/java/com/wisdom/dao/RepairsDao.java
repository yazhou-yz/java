package com.wisdom.dao;

import java.util.List;
import java.util.Map;


public interface RepairsDao {
	//用户添加
	public boolean add(String repairsType,String repairsNeed,String repairsState,String repairsTime,String repairsOid, String repairsTrace);
	//用户编辑(可以编辑报修类型、报修描述)
	public boolean editOwner(String repairsOid ,String repairsType,String repairsNeed);
	//管理员编辑(可以编辑报修状态)
	public boolean editStaff(String field,String value,String repairsId, String repairsTrace);
	//查询全表
	public List<Map<String, Object>> search(Integer page,Integer limit);
	//查询分页总条数
	public Integer searchCount();
	//通过用户手机号id查询报修的类型、报修的状态
	public List<Map<String, Object>> searchRepairsForOid(String repairsOid);
	//根据报修类型查询
	public List<Map<String, Object>> searchTypeForRepairs(String repairsType);
	//根据报修状态查询
	public List<Map<String, Object>> searchStateForRepairs(String repairsState);
}
