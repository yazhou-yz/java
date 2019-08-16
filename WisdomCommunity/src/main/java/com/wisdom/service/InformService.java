package com.wisdom.service;

import java.util.Map;

public interface InformService {
	//添加
	public Map<String, Object> add(String informTime, String informTitle, String informMessage);
	//编辑
	public Map<String, Object> edit(Integer informId, String informTitle,String informMessage);
	//查看
	public Map<String, Object> search(Integer page, Integer limit, Integer informId);
	//查询最新通知的表头
	public Map<String, Object> searchTitle();
}
