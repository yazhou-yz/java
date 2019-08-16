package com.wisdom.dao;

import java.util.List;
import java.util.Map;

import com.wisdom.model.Invitation;

public interface ForumDAO {
	public boolean addcomMent(String content, String owner_id, int invitation_id,String comment_date);
	public Invitation searchInvitation(Integer forum_id);
	public boolean AddForum(String owner_id,String theme,String content,String img, String forum_date);
	public List<Map<String, Object>> searchforum(Integer page);
	public Integer getCount();
	public List<Map<String, Object>> searchAllCount();
}
