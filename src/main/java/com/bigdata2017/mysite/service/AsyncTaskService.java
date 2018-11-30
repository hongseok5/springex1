package com.bigdata2017.mysite.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 비동기로 사용할 메소드들을 모아놓은 서비스 클래스
 * executor를 다중으로 사용할 경우 클래스를 따로 만들어준다
 * @author 오홍석
 * 	
 */

@Service("asyncTaskService")
public class AsyncTaskService {
	
	@Async("executorSmlt")
	public void executorSmlt(String str) {		
		System.out.println("executorSmlt " + str + Thread.currentThread().getName());		
	}
		
	@Async("executorSmlt")
	public void executorSmlt1(String str) {		
		System.out.println("executorSmlt1 " +  str + Thread.currentThread().getName());		
	}

}
