package com.wisdom.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Owner {
	private String ownerId;
	private String ownerName;
	private String ownerPassword;
	private String ownerHid;
	private Integer ownerLevel;
	private String ownerHeadicon;
	private House house;
}
