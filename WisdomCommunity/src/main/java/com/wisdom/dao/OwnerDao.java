package com.wisdom.dao;

import java.util.List;

import com.wisdom.model.Owner;

public interface OwnerDao {
	Boolean register(String ownerId, String ownerName, String ownerHid, String ownerPassword, String visitPath);

	Integer register_exist(String ownerId);

	Owner login(Owner owner);

	Boolean update(String oldPwd, String newPwd, String ownerId);

	Boolean delete(String id);

	List<Owner> search(Integer page, Integer limit);

	Integer getCount();

	List<Owner> searchByName(String ownerName);
}
