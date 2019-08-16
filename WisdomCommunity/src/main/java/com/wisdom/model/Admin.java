package com.wisdom.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin implements Serializable {
	private static final long serialVersionUID = -4203692764104946975L;
	private Integer adminId;
	private String adminName;
	private String adminPassword;
	private Integer adminLevel;
}
