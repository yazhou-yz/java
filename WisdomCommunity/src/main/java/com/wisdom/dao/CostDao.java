package com.wisdom.dao;

import java.util.List;
import java.util.Map;




public interface CostDao {
	
	//添加
	public boolean add(Double costProperty, String costStatus, String costOid,String costHid,String costTime);
	
	//编辑
	public boolean edit(String costHid,String field, String value);
	
//	//编辑金额 
//	public boolean editProperty(String costHid,Double costProperty);
//			
//	//编辑缴费状态
//	public boolean editStatus(String costHid,String costStatus);
	
	//查询全表
	public List<Map<String, Object>> search();
	// 查询全表总记录条数
	public int searchAllCount();
	
	//根据业主id查询
	public List<Map<String, Object>> searchByOwnerId(String costOid);
			
	//根据门牌号查询
	public List<Map<String, Object>> searchByHouseId(String costHid);
			
	//根据缴费状态查询
	public List<Map<String, Object>> searchCostStatus(String costStatus);
}
