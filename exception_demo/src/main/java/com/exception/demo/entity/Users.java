package com.exception.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "User_Details")
public class Users {
	@Id
	@GeneratedValue
	private int userId;

	private String name;

	private int age;

	private String email;

	private String phone;

	private String nationality;
}
