package com.wisdom.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface CarService {
	// 增加
	public Map<String, Object> add(String carDestination, String carPlate, String carTime, String carOid,
			Integer carSeat, MultipartFile file);

	// 一重判断:根据用户id判断用户是否有车,如果有车获得车牌信息.
	public Map<String, Object> verify(String carOid);

	// 查询: 查询所有发布信息(service层做时间过滤功能)
	public Map<String, Object> searchAll();

	Map<String, Object> searchOneForCarId(String oid);

	// 查询: 用户根据car_id查询单条拼车信息.
	public Map<String, Object> searchOneForCarId(Integer carId);

	// 编辑: 用户根据查到的car_id对单条拼车信息进行目的地、出发时间、座位数的编辑修改.
	public Map<String, Object> edit(String carDestination, String carTime, Integer carSeat, Integer carId);

	// 删除(用户根据car_id条件对单条发布的拼车信息进行删除.)
	public Map<String, Object> delete(Integer carId);

}
