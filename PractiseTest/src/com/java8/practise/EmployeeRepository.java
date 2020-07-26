package com.java8.practise;

public class EmployeeRepository {
	
	public static Employee findById(Integer id) {
		return new Employee(id,"test"+id,56,1000000);
	}
}
