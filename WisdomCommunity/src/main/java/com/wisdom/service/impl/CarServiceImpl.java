package com.wisdom.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wisdom.commons.FilesUpload;
import com.wisdom.commons.FilesUploadz;
import com.wisdom.dao.CarDao;
import com.wisdom.service.CarService;

@Service
public class CarServiceImpl implements CarService {
	@Autowired
	private CarDao carDao;

	// 增加
	@SuppressWarnings("unused")
	@Override
	public Map<String, Object> add(String carDestination, String carPlate, String carTime, String carOid,
			Integer carSeat, MultipartFile file) {
		String carImg = null;
		if (file != null) {
			carImg = FilesUploadz.filePart(carOid, file);
		}
		Map<String, Object> result = new HashMap<>();
		if ((null == carDestination && null == carPlate && null == carTime && null == carOid)
				|| (carDestination.trim().equals("") && carPlate.trim().equals("") && carTime.trim().equals("")
						&& carOid.trim().equals(""))) {
			result.put("code", 2);
			result.put("msg", "需添加的信息有误");
			return result;
		}
		boolean convertSuccess = true;
		// 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			// 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
			format.setLenient(false);
			// 使用parse判断
			format.parse(carTime);
		} catch (Exception e) {
			convertSuccess = false;
		}
		if (convertSuccess = false) {
			result.put("code", 2);
			result.put("msg", "发布拼车时间格式有误");
			return result;
		}
		Date date = new Date();// 当前时间
		Date dateCar;
		try {
			dateCar = format.parse(carTime);
			if (!(dateCar.before(date))) { // true dateCar时间比date(当前时间)早
				boolean success = carDao.add(carDestination, carPlate, carTime, carOid, carSeat, carImg);
				if (success) {
					result.put("code", 0);
					result.put("msg", "发布拼车信息成功");
				} else {
					result.put("code", 1);
					result.put("msg", "发布拼车信息失败");
				}
				return result;
			}
		} catch (ParseException e) {
			result.put("code", 3);
			result.put("msg", "ParseException异常");
			e.printStackTrace();
		}
		return result;
	}
//       boolean success = carDao.add(carDestination, carPlate, carTime, carOid, carSeat);
//       if(success) {
//    	   result.put("code", 0);
//    	   result.put("msg", "发布拼车信息成功");
//       }else { 
//    	   result.put("code", 1);
//    	   result.put("msg", "发布拼车信息失败");
//	}
//		return result;

	// 一重判断:根据用户id判断用户是否有车,如果有车获得车牌信息.
	@Override
	public Map<String, Object> verify(String carOid) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (null == carOid || carOid.trim().equals("")) {
			result.put("code", 2);
			result.put("msg", "车牌信息有误");
			return result;
		} else {
			List<Map<String, Object>> data = carDao.verify(carOid);
			if (data.size() <= 0) {
				result.put("code", 1);
				result.put("msg", "未查询到车辆信息");
			}
			result.put("code", 0);
			result.put("msg", "查询成功");
			result.put("data", data);
		}
		return result;
	}

	// 查询: 查询所有发布信息(service层做时间过滤功能)
	@Override
	public Map<String, Object> searchAll() {
		Map<String, Object> result = new HashMap<>();
		List<Map<String, Object>> dataOld = carDao.searchAll();
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		// 遍历结果集 进行时间过滤功能
		for (Map<String, Object> map : dataOld) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();// 当前时间
			String timeCar = (String) map.get("car_time");
			Date dateCar;
			try {
				dateCar = dateFormat.parse(timeCar);// 拼车时间
				if (!(dateCar.before(date))) {
					data.add(map);
				}
				// okTime=true dateCar时间比date(当前时间)早
			} catch (ParseException e) {
				System.out.println("时间转化异常");
				result.put("code", 3);
				result.put("msg", "时间转化异常");
				e.printStackTrace();
				return result;
			}
		}
		if (data.size() <= 0 || null == data) {
			result.put("code", 1);
			result.put("msg", "查询失败");
		} else {
			result.put("code", 0);
			result.put("msg", "查询成功");
			result.put("data", data);
		}
		return result;
	}

	// 查询: 用户根据car_id查询单条拼车信息.
	@Override
	public Map<String, Object> searchOneForCarId(Integer carId) {
		Map<String, Object> result = new HashMap<>();
		if (null == carId || carId <= 0) {
			result.put("code", 2);
			result.put("msg", "查询条件有误");
			return result;
		}
		Map<String, Object> data = carDao.searchOneForCarId(carId);
		if (data.size() <= 0 || null == data) {
			result.put("code", 1);
			result.put("msg", "根据条件未查询到结果");
		} else {
			result.put("code", 0);
			result.put("msg", "查询成功");
			result.put("data", data);
		}
		return result;
	}

	// 编辑: 用户根据查到的car_id对单条拼车信息进行目的地、出发时间、座位数的编辑修改.
	@Override
	public Map<String, Object> edit(String carDestination, String carTime, Integer carSeat, Integer carId) {
		Map<String, Object> result = new HashMap<>();
		if (null == carId || carId <= 0) {
			result.put("code", 2);
			result.put("msg", "当前编辑信息有误");
			return result;
		}
		// 编辑的新时间不能小于当前时间
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();// 当前时间
		Date dateCar;
		try {
			dateCar = format.parse(carTime);
			if (!(dateCar.before(date))) { // true dateCar时间比date(当前时间)早
				boolean success = carDao.edit(carDestination, carTime, carSeat, carId);
				if (success) {
					result.put("code", 0);
					result.put("msg", "编辑拼车信息成功");
				} else {
					result.put("code", 1);
					result.put("msg", "编辑拼车信息失败");
				}
				return result;
			}
		} catch (ParseException e) {
			result.put("code", 3);
			result.put("msg", "ParseException异常");
			e.printStackTrace();
		}
		return result;
	}

	// 删除(用户根据car_id条件对单条发布的拼车信息进行删除.)
	public Map<String, Object> delete(Integer carId) {
		Map<String, Object> result = new HashMap<>();
		if (null == carId || carId <= 0) {
			result.put("code", 1);
			result.put("msg", "删除失败");
			return result;
		}
		boolean success = carDao.delete(carId);
		if (success) {
			result.put("code", 0);
			result.put("msg", "删除当前拼车信息成功");
		} else {
			result.put("code", 1);
			result.put("msg", "删除当前拼车信息失败");
		}
		return result;
	}

	@Override
	public Map<String, Object> searchOneForCarId(String oid) {
		Map<String, Object> result = new HashMap<>();

		String icon = carDao.searchIconByOid(oid);
		if (icon != null) {
			result.put("code", 0);
			result.put("data", icon);
		} else {
			result.put("code", 1);
			result.put("msg", "没有找到头像");
		}
		return result;
	}
}
