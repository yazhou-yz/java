package com.wisdom.dao;

import java.util.List;
import java.util.Map;

import com.wisdom.model.House;

//HouseDao
public interface HouseDao {

	// 添加
	public boolean add(String houseId, Double houseArea, String houseOwner);

	// 添加时id重复校验
	public int seachHouseId(String houseId);

	// 编辑
	public boolean edit(String houseId, Double houseArea, String houseOwner);

	// 编辑面积
	public boolean editArea(String houseId, Double houseArea);

	// 编辑业主名
	public boolean editHouseOwner(String houseId, String houseOwner);

	// 查询
	// 查询全表
	public List<Map<String, Object>> search();

	// 根据门牌号查询单条房屋业主信息
	public List<House> searchHouseIdForOwner(String houseId);

	// 根据业主名查询房屋信息
	public List<House> searchHouseOwnerForHouse(String houseOwner);

	// 查询全表总记录条数
	public int searchAllCount();

	public Boolean batchAdd(List<House> list);

	public Boolean houseEdit(String field, String value, String houseId);

	public List<Map<String, Object>> getHouseIdByOwnerId(String oId);
}
