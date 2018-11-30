package com.bigdata2017.mysite.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * �񵿱�� ����� �޼ҵ���� ��Ƴ��� ���� Ŭ����
 * executor�� �������� ����� ��� Ŭ������ ���� ������ش�
 * @author ��ȫ��
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
