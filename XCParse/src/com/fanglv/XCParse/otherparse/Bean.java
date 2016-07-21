package com.fanglv.XCParse.otherparse;

import java.util.LinkedHashMap;

import com.fanglv.XCParse.data.DataInstanceInterface;

public class Bean implements DataInstanceInterface{
	private String name;
	private int age;
	private InBean address;
	
	public InBean getAddress() {
		return address;
	}
	public void setAddress(InBean address) {
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Bean [name=" + name + ", age=" + age + ", address=" + address
				+ "]";
	}
	@Override
	public LinkedHashMap<String, Boolean> getDataName() {
		
		LinkedHashMap<String, Boolean> map = new LinkedHashMap<String, Boolean>();
		map.put("age", false);
		map.put("name", false);
		
		return map;
	}
	
	
}
