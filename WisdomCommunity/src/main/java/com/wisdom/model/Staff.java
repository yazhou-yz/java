package com.wisdom.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//物业人员
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Staff implements Serializable {

	
	private static final long serialVersionUID = -6025094095107760194L;
	
	private int staffId;
	private String staffName;
	private String staffType;
	private String staffPhone;
	private int staffStatus; //状态
	

}
