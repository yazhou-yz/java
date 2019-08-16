package com.wisdom.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Repairs implements Serializable {
	private static final long serialVersionUID = -1276594919524614614L;
	private int repaisId;
	private String repairsType;
	private String repairsNeed;
	private String repairsState;
	private String repairsTime;
	private String repairsOid;
	private String repairsTrace;

}
