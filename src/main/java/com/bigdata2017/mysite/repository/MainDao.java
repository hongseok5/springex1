package com.bigdata2017.mysite.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MainDao {
	
	@Autowired
	private SqlSession sqlSession;

	public List<Map<String, Object>> getEmployees(Map<String, Object> map){
		List<Map<String, Object>> list = sqlSession.selectList("main.selectEmpList", map);
		return list;
	}
	
	public int getEmployeesNum(Map<String, Object> map){
		int counts = sqlSession.selectOne("main.selectEmpCount", map);
		return counts;
	}
}
