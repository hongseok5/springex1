package com.bigdata2017.mysite.configure;

import java.util.concurrent.Executor;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

/*
 *  컴포넌트 스캔 설정을 applicationContext.xml에서 설정해 주면 @Async가 제대로 동작
 */

@Configuration
@ComponentScan("com.bigdata2017.mysite.*")
@EnableAsync
public class AsyncConfigure implements AsyncConfigurer {
	
	   /** 시뮬레이션 Thread */
    private static ThreadPoolTaskExecutor EXECUTOR_SMLT;
    /** 시뮬레이션 기본 Thread 수 */
    private static int TASK_SMLT_CORE_POOL_SIZE = 2;
    /** 시뮬레이션 최대 Thread 수 */
    private static int TASK_SMLT_MAX_POOL_SIZE = 10;
    /** 시뮬레이션 QUEUE 수 */
    private static int TASK_SMLT_QUEUE_CAPACITY = 0;
    
    // executor를 다중으로 정의하여 다른 설정의 executor를 사용할 수 있다. 하나의 executor를 사용할 때는 굳이 Bean 설정을 하지 않아도 괜찮다. 
    @Bean(name = "executorSmlt")
	@Override
	public Executor getAsyncExecutor() {
		
        EXECUTOR_SMLT = new ThreadPoolTaskExecutor();
        EXECUTOR_SMLT.setCorePoolSize(TASK_SMLT_CORE_POOL_SIZE);
        EXECUTOR_SMLT.setMaxPoolSize(TASK_SMLT_MAX_POOL_SIZE);
        EXECUTOR_SMLT.setQueueCapacity(TASK_SMLT_QUEUE_CAPACITY);
        EXECUTOR_SMLT.setBeanName("executorSmlt");
        EXECUTOR_SMLT.initialize();
        
        return EXECUTOR_SMLT;
	}
    
	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		// TODO Auto-generated method stub
		return new AsyncExceptionHandler();
	}
	
    /**
     * 시뮬레이션 Thread 등록 가능 여부
     *
     * @return 실행중인 task 개수가 최대 개수(max + queue)보다 크거나 같으면 false
     */
	
    public static boolean isSmltTaskExecutable() {
        boolean rtn = true;
 
        // 실행중인 task 개수가 최대 개수(max + queue)보다 크거나 같으면 false
        if (EXECUTOR_SMLT.getActiveCount() >= (TASK_SMLT_MAX_POOL_SIZE + TASK_SMLT_QUEUE_CAPACITY)) {
            rtn = false;
        }
 
        return rtn;
    }
    
    /**
     * 시뮬레이션 Thread 등록 가능 여부
     *
     * @param createCnt : 생성 개수
     * @return 실행중인 task 개수 + 실행할 개수가 최대 개수(max + queue)보다 크거나 같으면 false
     */
    public static boolean isSmltTaskExecute(int createCnt) {
        boolean rtn = true;
 
        // 실행중인 task 개수 + 실행할 개수가 최대 개수(max + queue)보다 크거나 같으면 false
        if ((EXECUTOR_SMLT.getActiveCount() + createCnt) >= (TASK_SMLT_MAX_POOL_SIZE + TASK_SMLT_QUEUE_CAPACITY)) {
            rtn = false;
        }
 
        return rtn;
    }
    
}
