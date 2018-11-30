package com.bigdata2017.mysite.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import com.bigdata2017.mysite.repository.MainDao;

@Service
public class MainSerivce {
	
	@Autowired
	MainDao maindao;
	
	@Async("executorSmlt")
	public void asyncTest() {		
		
		//System.out.println("Async method test - Service (void method) " + Thread.currentThread().getName() );				
	}
	
	@Async("executorSmlt")
	public Future<String> asyncReturnTest() {		
		System.out.println("Async method test - Service (return value method) " + Thread.currentThread().getName() );		
		return new AsyncResult<String>("Hello!");
	}
	
	public List<Map<String, Object>> getEmployees(Map<String, Object> map){
		return maindao.getEmployees(map);
	}
	
	public Integer getEmployeesNum(Map<String, Object> map){
		return maindao.getEmployeesNum(map);
	}

}
