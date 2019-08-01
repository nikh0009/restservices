package com.nikhiltripathy.restful.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;

import com.nikhiltripathy.restful.model.Employee;


/*
 * Handlde request for employee service
 * */

@Controller
public class EmployeeController {
	
	public static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	
	Map <Integer,Employee> empData = new HashMap<Integer,Employee>();
	
	
	@RequestMapping(value = EmployeeRestConstants.DUMMY_EMP,method=RequestMethod.GET)
	public @ResponseBody Employee getDummyEmployee() {
		
		logger.info("Start getDummyEmployee");
		Employee emp = new Employee();
		emp.setId(999);
		emp.setName("Nikhil");
		emp.setCreatedDate(new Date());
		empData.put(999, emp);
		return emp;
	}
	
	@RequestMapping(value = EmployeeRestConstants.GET_EMP, method=RequestMethod.GET)
	public @ResponseBody Employee getEmployee(@PathVariable("id") int empId) {
		logger.info("Start getEmployee. Id="+empId);
		return empData.get(empId);
		
	}
	
	@RequestMapping(value = EmployeeRestConstants.GET_ALL_EMP, method=RequestMethod.GET)
	public @ResponseBody List<Employee> getAllEmpoyees(){
		logger.info("Start getAllEmployees");
		List<Employee> emps = new ArrayList<Employee>();
		Set<Integer> empIds = empData.keySet();
		for(Integer i: empIds) {
			emps.add(empData.get(i));
		}
		 
		return emps;
	}
	
	@RequestMapping(value = EmployeeRestConstants.CREATE_EMP, method=RequestMethod.POST)
	public @ResponseBody Employee createEmployee(@RequestBody Employee emp) {
		logger.info("createEmployee started!");
		emp.setCreatedDate(new Date());
		empData.put(emp.getId(),emp);
		logger.info("done creating!");
		return emp;
	}
	
	@RequestMapping(value = EmployeeRestConstants.DELETE_EMP, method=RequestMethod.PUT)
	public @ResponseBody Employee deleteEmployee(@PathVariable("id") int empId) {
		logger.info("Started deleteEmployee. Id="+empId);
		Employee emp = empData.get(empId);
		empData.remove(empId);
		return emp;
	}
}
