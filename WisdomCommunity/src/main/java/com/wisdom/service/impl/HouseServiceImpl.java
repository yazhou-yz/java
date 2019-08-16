package com.wisdom.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.wisdom.dao.HouseDao;
import com.wisdom.model.House;
import com.wisdom.service.HouseService;

@Service
public class HouseServiceImpl implements HouseService {
	@Autowired
	private HouseDao houseDao;

	// 添加
	@Override
	public Map<String, Object> add(String houseId, Double houseArea, String houseOwner) {
		int houseIdCount = houseDao.seachHouseId(houseId);
		Map<String, Object> result = new HashMap<String, Object>();
		if (houseIdCount > 0) {
			// 进if证明添加房产时,该房子门牌号已存在.
			result.put("code", 2);
			result.put("msg", "该门牌号已存在,请管理员核实房屋信息");
			return result;
		}
		if (null == houseArea || (houseArea instanceof Double) == false || houseArea <= 0 || houseArea >= 200) {
			result.put("code", 3);
			result.put("msg", "房屋面积输入有误,请管理员核实");
			return result;
		}
		if (houseOwner.equals(" ") || null == houseOwner) {
			houseOwner = "无";
		}
		boolean success = houseDao.add(houseId, houseArea, houseOwner);
		if (success) {
			result.put("code", 0);
			result.put("msg", "添加成功");
		} else {
			result.put("code", 1);
			result.put("msg", "sql语句异常");
		}
		return result;
	}

	// 编辑
	@Override
	public Map<String, Object> edit(String houseId, Double houseArea, String houseOwner) {
		Map<String, Object> result = new HashMap<>();
		if (null == houseId || !(Integer.parseInt(houseId) > 0)) {
			result.put("code", 1);
			result.put("msg", "修改失败,请确认门牌号是否正确");
			return result;
		} else {
			if (null != houseArea && null != houseOwner) {
				boolean success = houseDao.edit(houseId, houseArea, houseOwner);
				if (success) {
					result.put("code", 0);
					result.put("msg", "修改成功");
				} else {
					result.put("code", 1);
					result.put("msg", "修改失败");
				}
			}
			if (null != houseArea && houseArea > 0 && (null == houseOwner || houseOwner.trim() == "")) {
				boolean success = houseDao.editArea(houseId, houseArea);
				if (success) {
					result.put("code", 0);
					result.put("msg", "修改成功");
				} else {
					result.put("code", 1);
					result.put("msg", "修改失败");
				}
			} else {
				result.put("code", 1);
				result.put("msg", "修改失败,请确认门牌号和面积是否正确");
			}
			if (null != houseOwner && !(houseOwner.trim().equals(" ")) && null == houseArea
					&& !(houseArea instanceof Number)) {
				boolean success = houseDao.editHouseOwner(houseId, houseOwner);
				if (success) {
					result.put("code", 0);
					result.put("msg", "修改成功");
				} else {
					result.put("code", 1);
					result.put("msg", "修改失败");
				}
			} else {
				result.put("code", 1);
				result.put("msg", "修改失败");
			}
		}
		return result;
	}

	// 查询全表
	@Override
	public Map<String, Object> search(int page, int limit) {
		PageHelper.startPage(page, limit);
		List<Map<String, Object>> data = houseDao.search();
		Map<String, Object> result = new HashMap<>();
		if (null == data || !(data.size() > 0)) {
			result.put("code", 1);
			result.put("msg", "抱歉,查询结果为空");
		} else {
			result.put("code", 0);
			result.put("msg", "查询成功");
			result.put("data", data);
			result.put("count", houseDao.searchAllCount());
		}
		return result;
	}

	// 添加时id重复校验
	@Override
	public int seachHouseId(String houseId) {
		return houseDao.seachHouseId(houseId);
	}

	// 根据门牌号查询单条房屋业主信息
	@Override
	public Map<String, Object> searchHouseIdForOwner(String houseId) {
		Map<String, Object> result = new HashMap<>();
		if (null == houseId) {
			result.put("code", 1);
			result.put("msg", "查询失败或未查询到数据");
			return result;
		}
		List<House> owners = houseDao.searchHouseIdForOwner(houseId);
		result.put("code", 0);
		result.put("msg", "查询成功");
		result.put("data", owners);
		return result;
	}

	// 根据业主名查询房屋信息
	public Map<String, Object> searchHouseOwnerForHouse(String houseOwner) {
		List<House> houses = houseDao.searchHouseOwnerForHouse(houseOwner);
		Map<String, Object> result = new HashMap<>();
		if (!(houses.size() > 0) || null == houses) {
			result.put("code", 1);
			result.put("msg", "未查询到数据");
		} else {
			result.put("code", 0);
			result.put("msg", "查询成功");
//			Map<String, Object> data = new HashMap<>();
//			for (House house : houses) {
//				data.put("houseId", house.getHouseId());
//				data.put("houseArea", house.getHouseArea());
//				data.put("houseOwner", house.getHouseOwner());
//			}
			result.put("data", houses);
		}
		return result;
	}

	@Override
	public Map<String, Object> batchAdd(Integer building, Integer uniterm, Integer floor, Integer num) {
		List<House> list = new ArrayList<House>();
		Random r = new Random();
		for (int i = 1; i <= floor; i++) {
			for (int m = 1; m <= uniterm; m++) {
				for (int j = 1; j <= num; j++) {
					String house_id = building + "-" + m + "-";
					if (i < 10) {
						house_id += "0";
					}
					House house = new House();
					house_id += i + "-" + (i + "0" + j);
					house.setHouseId(house_id);
					house.setHouseArea(r.nextInt(50) + 100.0);
					list.add(house);
				}
			}
		}
		Map<String, Object> result = new HashMap<String, Object>();
		if (houseDao.batchAdd(list)) {
			result.put("code", 0);
			result.put("msg", "添加成功");
		} else {
			result.put("code", 1);
			result.put("msg", "添加失败");
		}
		return result;
	}

	@Override
	public Map<String, Object> houseEdit(String field, String value, String houseId) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (houseDao.houseEdit(field, value, houseId)) {
			result.put("code", 0);
			result.put("msg", "修改成功");
		} else {
			result.put("code", 1);
			result.put("msg", "修改失败");
		}
		return result;
	}

}
