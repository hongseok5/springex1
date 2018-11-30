package com.bigdata2017.mysite.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskRejectedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bigdata2017.mysite.configure.AsyncConfigure;
import com.bigdata2017.mysite.service.AsyncTaskService;
import com.bigdata2017.mysite.service.MainSerivce;



@Controller
public class MainController {
	
	@Autowired
	private MainSerivce mainService;
	
	@Resource(name = "asyncTaskService")
	private AsyncTaskService asyncTaskService;
	
	@RequestMapping("/employee")
	public ModelAndView index(@RequestParam( value="page", required=true, defaultValue="1") Integer page,
							  @RequestParam( value="key", required=true, defaultValue="") String keyword,
							  @RequestParam( value="size", required=true, defaultValue="20") Integer size ) {
		
/*		System.out.println("Async method test - Controller " + Thread.currentThread().getName() );
		mainService.asyncTest();
		Object obj = mainService.asyncReturnTest();
		System.out.println(obj.toString());*/
		ModelAndView mv = new ModelAndView("main/index");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("page", page);
		paramMap.put("keyword", keyword);
		paramMap.put("size", size);
		
		mv.addObject("data", mainService.getEmployees(paramMap));
		mv.addObject("counts", mainService.getEmployeesNum(paramMap));
		System.out.println(mainService.getEmployees(paramMap));
		return mv;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping("/executorSmlt")
	public ModelAndView doTask() throws Exception {
		
		try {
			if(AsyncConfigure.isSmltTaskExecutable()) {
				asyncTaskService.executorSmlt("First ");
				asyncTaskService.executorSmlt1("Second ");
				asyncTaskService.executorSmlt("Third ");
				asyncTaskService.executorSmlt1("Fourth ");
				asyncTaskService.executorSmlt("fifth ");
				asyncTaskService.executorSmlt1("sixth ");
			} else {
				System.out.println(">>>>>>>>>>>>>Thread ���� �ʰ�");
			}
		} catch (TaskRejectedException e) {
			System.out.println(">>>>>>>>>>>>> Thread ����");
			System.out.println("��� ���� �ʰ�");
			System.out.println("============= Thread End");
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("main/sample");
		String strName = "Oh Hongseok";
		mav.addObject("name",strName);
		return mav;
	}

}
