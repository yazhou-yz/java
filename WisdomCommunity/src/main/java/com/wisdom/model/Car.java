package com.wisdom.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {
	private Integer carId;
	private String carDestination;
	private String carPlate;
	private String carTime;
	private String carMessage;
	private String carOid;
	private Integer carSeat;
}



 