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
 *  ������Ʈ ��ĵ ������ applicationContext.xml���� ������ �ָ� @Async�� ����� ����
 */

@Configuration
@ComponentScan("com.bigdata2017.mysite.*")
@EnableAsync
public class AsyncConfigure implements AsyncConfigurer {
	
	   /** �ùķ��̼� Thread */
    private static ThreadPoolTaskExecutor EXECUTOR_SMLT;
    /** �ùķ��̼� �⺻ Thread �� */
    private static int TASK_SMLT_CORE_POOL_SIZE = 2;
    /** �ùķ��̼� �ִ� Thread �� */
    private static int TASK_SMLT_MAX_POOL_SIZE = 10;
    /** �ùķ��̼� QUEUE �� */
    private static int TASK_SMLT_QUEUE_CAPACITY = 0;
    
    // executor�� �������� �����Ͽ� �ٸ� ������ executor�� ����� �� �ִ�. �ϳ��� executor�� ����� ���� ���� Bean ������ ���� �ʾƵ� ������. 
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
     * �ùķ��̼� Thread ��� ���� ����
     *
     * @return �������� task ������ �ִ� ����(max + queue)���� ũ�ų� ������ false
     */
	
    public static boolean isSmltTaskExecutable() {
        boolean rtn = true;
 
        // �������� task ������ �ִ� ����(max + queue)���� ũ�ų� ������ false
        if (EXECUTOR_SMLT.getActiveCount() >= (TASK_SMLT_MAX_POOL_SIZE + TASK_SMLT_QUEUE_CAPACITY)) {
            rtn = false;
        }
 
        return rtn;
    }
    
    /**
     * �ùķ��̼� Thread ��� ���� ����
     *
     * @param createCnt : ���� ����
     * @return �������� task ���� + ������ ������ �ִ� ����(max + queue)���� ũ�ų� ������ false
     */
    public static boolean isSmltTaskExecute(int createCnt) {
        boolean rtn = true;
 
        // �������� task ���� + ������ ������ �ִ� ����(max + queue)���� ũ�ų� ������ false
        if ((EXECUTOR_SMLT.getActiveCount() + createCnt) >= (TASK_SMLT_MAX_POOL_SIZE + TASK_SMLT_QUEUE_CAPACITY)) {
            rtn = false;
        }
 
        return rtn;
    }
    
}
