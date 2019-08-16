package com.wisdom.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cost {
	
	private String costId;
	
	private Double costProperty;
	
	private String costStatus;
	
	private String costOid;
	private String costHid;
	private String costTime;
	
	
}
