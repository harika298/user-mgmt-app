package com.management.user.models;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RoleModel {
	
	private int roleId;
	private String name;
	private String description;
	private Date createdDate;
	private Date modifiedDate;
}