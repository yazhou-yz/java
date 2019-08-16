package com.wisdom.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wisdom.service.CarService;

@RestController
@RequestMapping("/car")
public class CarController {
	@Autowired
	private CarService carService;

	// 增加
	@RequestMapping("/add")
	public Map<String, Object> add(String carDestination, String carPlate, String carTime, String carOid,
			Integer carSeat, MultipartFile file) {
		return carService.add(carDestination, carPlate, carTime, carOid, carSeat, file);
	}

	// 一重判断:根据用户id判断用户是否有车,如果有车获得车牌信息.
	@RequestMapping("/verify")
	public Map<String, Object> verify(String carOid) {
		System.out.println(carOid);
		return carService.verify(carOid);
	}

	// 查询: 查询所有发布信息(service层做时间过滤功能)
	@RequestMapping("/searchAll")
	public Map<String, Object> searchAll() {
		return carService.searchAll();
	}

	// 查询: 用户根据car_id查询单条拼车信息.
	@RequestMapping("/searchOneForCarId")
	public Map<String, Object> searchOneForCarId(Integer carId) {
		return carService.searchOneForCarId(carId);
	}

	@RequestMapping("/searchIconByOid")
	public Map<String, Object> searchOneForCarId(String oid) {
		return carService.searchOneForCarId(oid);
	}

	// 编辑: 用户根据查到的car_id对单条拼车信息进行目的地、出发时间、座位数的编辑修改.
	@RequestMapping("/edit")
	public Map<String, Object> edit(String carDestination, String carTime, Integer carSeat, Integer carId) {
		return carService.edit(carDestination, carTime, carSeat, carId);
	}

	// 删除(用户根据car_id条件对单条发布的拼车信息进行删除.)
	@RequestMapping("/delete")
	public Map<String, Object> delete(Integer carId) {
		return carService.delete(carId);
	}

}
