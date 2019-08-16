package com.wisdom.service;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;

public interface CostService {

		//添加
		public Map<String, Object> add(Double costProperty, String costStatus, String costOid,String costHid,String costTime);
			
		//编辑
		public Map<String, Object> edit(String costHid,String field, String value);
		
//		//编辑金额 
//		public Map<String, Object> editProperty(String costHid,Double costProperty);
//		
//		//编辑缴费状态
//		public Map<String, Object> editStatus(String costHid,String costStatus);
		
		//查询
		public Map<String, Object> search(Integer page,Integer limit);
		
		//根据业主id查询
		public Map<String, Object> searchByOwnerId(String costOid);
		
		//根据门牌号查询
		public Map<String, Object> searchByHouseId(String costHid);
		
		//根据缴费状态查询
		public Map<String, Object> searchCostStatus(String costStatus);
}
