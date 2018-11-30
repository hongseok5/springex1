package com.bigdata2017.mysite.configure;

import java.lang.reflect.Method;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

public class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

	@Override
	public void handleUncaughtException(Throwable throwable, Method method, Object... obj) {
        System.out.println("==============>>>>>>>>>>>> THREAD ERROR");
        System.out.println("Exception Message :: " + throwable.getMessage());
        System.out.println("Method Name :: " + method.getName());

        for (Object param : obj) {
            System.out.println("Parameter Value :: " + param);
        }

        System.out.println("==============>>>>>>>>>>>> THREAD ERROR END");

	}

}
