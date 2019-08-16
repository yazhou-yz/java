package com.wisdom.dao;

import java.util.List;
import java.util.Map;

public interface InformDao {
	// 添加
	public boolean add(String informTime, String informTitle, String informMessage);

	// 编辑
	public boolean edit(Integer informId, String informTitle, String informMessage);

	public boolean editTitle(Integer informId, String informTitle);

	public boolean editMessage(Integer informId, String informMessage);

	// 查看
	public List<Map<String, Object>> search();

	// 查询分页
	public int searchAllCount();

	// 查看通知详细内容
	public List<Map<String, Object>> searchMseeageForId(Integer informId);

	// 查询最新通知的表头
	public List<Map<String, Object>> searchTitle();
}
