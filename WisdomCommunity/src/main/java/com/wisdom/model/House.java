package com.wisdom.model;

import java.io.Serializable;

//房产
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class House implements Serializable {
	private static final long serialVersionUID = -2000168181586123798L;

	private String houseId;
	private Double houseArea;
	private String houseOwner;

}
