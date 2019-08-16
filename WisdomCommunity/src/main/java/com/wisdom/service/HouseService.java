package com.wisdom.service;

import java.util.Map;

public interface HouseService {
	// 添加
	public Map<String, Object> add(String houseId, Double houseArea, String houseOwner);

	// 编辑
	public Map<String, Object> edit(String houseId, Double houseArea, String houseOwner);

	public Map<String, Object> houseEdit(String field, String value, String houseId);

	// 查询
	public Map<String, Object> search(int page, int limit);

	// 根据门牌号查询
	public Map<String, Object> searchHouseIdForOwner(String houseId);

	// 根据用户名查询房屋信息
	public Map<String, Object> searchHouseOwnerForHouse(String houseOwner);

	// 添加时id重复校验
	public int seachHouseId(String houseId);

	public Map<String, Object> batchAdd(Integer building, Integer uniterm, Integer floor, Integer num);
}
