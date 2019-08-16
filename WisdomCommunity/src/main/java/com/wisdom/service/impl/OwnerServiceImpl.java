package com.wisdom.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.wisdom.commons.FilesUpload;
import com.wisdom.commons.FilesUploadz;
import com.wisdom.dao.HouseDao;
import com.wisdom.dao.OwnerDao;
import com.wisdom.model.Owner;
import com.wisdom.service.OwnerService;

@Service
public class OwnerServiceImpl implements OwnerService {
	@Autowired
	private OwnerDao ownerDao;
	@Autowired
	private HouseDao houseDao;
	private Map<String, Object> json = new HashMap<String, Object>();

	@Override
	public Map<String, Object> register(Owner owner, MultipartFile file) {
		List<Map<String, Object>> list = houseDao.getHouseIdByOwnerId(owner.getOwnerId());
		String ownerHid = "";
		if (list.size() != 0) {
			ownerHid = (String) list.get(0).get("house_id");
		}
		if (ownerDao.register_exist(owner.getOwnerId()) > 0) {
			json.put("code", 0);
			json.put("msg", "该用户已注册");
			return json;
		}
		if (!ownerHid.equals("")) {
			String visitPath = null;
			if (file != null) {
				visitPath = FilesUploadz.filePart(owner.getOwnerId(), file);
			}
			owner.setOwnerHid(ownerHid);
			if (ownerDao.register(owner.getOwnerId(), owner.getOwnerName(), owner.getOwnerHid(),
					owner.getOwnerPassword(), visitPath)) {
				json.put("code", 0);
				json.put("msg", "注册成功");
			} else {
				json.put("code", 1);
				json.put("msg", "注册失败");
			}
		} else {
			json.put("code", 2);
			json.put("msg", "手机号与门牌号不匹配，请联系物业");
		}
		return json;
	}

	@Override
	public Map<String, Object> login(Owner owner, HttpSession session) {
		Owner ownerInfo = ownerDao.login(owner);
		if (ownerInfo != null) {
			ownerInfo.setOwnerPassword(null);
			json.put("code", 0);
			json.put("msg", "登录成功");
			json.put("data", ownerInfo);
			session.setAttribute("owner", ownerInfo);
		} else {
			json.put("code", 1);
			json.put("msg", "账号或密码有误");
		}
		return json;
	}

	@Override
	public Map<String, Object> update(String oldPwd, String newPwd, String ownerId) {
		if (ownerDao.update(oldPwd, newPwd, ownerId)) {
			json.put("code", 0);
			json.put("msg", "修改成功");
		} else {
			json.put("code", 1);
			json.put("msg", "旧密码不正确");
		}
		return json;
	}

	@Override
	public Map<String, Object> delete(String id) {
		if (ownerDao.delete(id)) {
			json.put("code", 0);
			json.put("msg", "删除成功");
		} else {
			json.put("code", 1);
			json.put("msg", "删除失败");
		}
		return json;
	}

	@Override
	public Map<String, Object> logout(HttpSession session) {
		session.removeAttribute("owner");
		if (session.getAttribute("owner") == null) {
			json.put("code", 0);
			json.put("msg", "注销成功");
		} else {
			json.put("code", 1);
			json.put("msg", "注销失败");
		}
		return json;
	}

	@Override
	public Map<String, Object> search(Integer page, Integer limit) {
		if (page != null && limit != null) {
			PageHelper.startPage(page, limit);
		}
		List<Owner> list = ownerDao.search(page, limit);
		if (list != null) {
			json.put("code", 0);
			json.put("msg", "查询成功");
			json.put("data", list);
			json.put("count", ownerDao.getCount());
		} else {
			json.put("code", 1);
			json.put("msg", "没有结果");
		}
		return json;
	}

	@Override
	public Map<String, Object> searchByName(String ownerName) {
		List<Owner> list = ownerDao.searchByName(ownerName);
		if (list != null) {
			json.put("code", 0);
			json.put("data", list);
			json.put("count", ownerDao.getCount());
		} else {
			json.put("code", 1);
			json.put("msg", "没有结果");
		}
		return json;
	}

}
