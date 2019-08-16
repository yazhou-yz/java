package com.wisdom.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.wisdom.dao.InformDao;
import com.wisdom.service.InformService;

@Service
public class InformServiceImpl implements InformService {
	@Autowired
	private InformDao informDao;
	// 添加

	@Override
	public Map<String, Object> add(String informTime, String informTitle, String informMessage) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (null == informTime || null == informTitle || null == informMessage) {
			result.put("code", 2);
			result.put("msg", "发布时间、标题、内容为空");
			return result;
		}
		boolean success = informDao.add(informTime, informTitle, informMessage);
		if (success) {
			result.put("code", 0);
			result.put("msg", "添加成功");
		} else {
			result.put("code", 1);
			result.put("msg", "添加失败,请检查sql语句");
		}
		return result;
	}

	// 编辑
	@Override
	public Map<String, Object> edit(Integer informId, String informTitle, String informMessage) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (null == informId || informId <= 0) {
			result.put("code", 2);
			result.put("msg", "删除条件不符合要求");
			return result;
		}
		if (null != informTitle && null != informMessage) {
			boolean success = informDao.edit(informId, informTitle, informMessage);
			if (success) {
				result.put("code", 0);
				result.put("msg", "编辑成功");
			} else {
				result.put("code", 1);
				result.put("msg", "编辑失败,请检查sql语句");
			}
		} else if (null != informTitle && null == informMessage) {
			boolean success = informDao.editTitle(informId, informTitle);
			if (success) {
				result.put("code", 0);
				result.put("msg", "编辑成功");
			} else {
				result.put("code", 1);
				result.put("msg", "编辑失败,请检查sql语句");
			}
		} else if (null == informTitle && null != informMessage) {
			boolean success = informDao.editMessage(informId, informMessage);
			if (success) {
				result.put("code", 0);
				result.put("msg", "编辑成功");
			} else {
				result.put("code", 1);
				result.put("msg", "编辑失败,请检查sql语句");
			}
		}
		return result;
	}

	@Override
	// 查询
	public Map<String, Object> search(Integer page, Integer limit, Integer informId) {
		if (page != null && limit != null) {
			PageHelper.startPage(page, limit);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		if (null == informId || informId <= 0) {
			List<Map<String, Object>> data = informDao.search();
			if (null == data || !(data.size() > 0)) {
				result.put("code", 1);
				result.put("msg", "抱歉,查询结果为空");
			} else {
				result.put("code", 0);
				result.put("msg", "查询成功");
				result.put("data", data);
				result.put("count", informDao.searchAllCount());
			}
		} else if (null != informId && informId > 0) {
			List<Map<String, Object>> data = informDao.searchMseeageForId(informId);
			if (null == data || !(data.size() > 0)) {
				result.put("code", 1);
				result.put("msg", "抱歉,查询结果为空");
			} else {
				result.put("code", 0);
				result.put("msg", "查询成功");
				result.put("data", data);
			}
		}
		return result;
	}

	// 查询最新通知的表头
	public Map<String, Object> searchTitle() {
//		Integer searchCount = informDao.searchAllCount();
		Map<String, Object> result = new HashMap<>();
		List<Map<String, Object>> data = informDao.searchTitle();
		if (data.size() <= 0 || null == data) {
			result.put("code", 1);
			result.put("msg", "抱歉,查询结果为空");
		}
		result.put("code", 0);
		result.put("msg", "查询成功");
		result.put("data", data);
		return result;
	}

}
