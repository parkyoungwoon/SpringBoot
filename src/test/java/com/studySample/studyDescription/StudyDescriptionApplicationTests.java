package com.studySample.studyDescription;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class StudyDescriptionApplicationTests {

	@Autowired
	private ApplicationContext context;

	@Autowired
	private SqlSessionFactory sessionFactory;

	@Test
	void contextLoads() {
	}

	@Test
	public void testByApplicationContext(){
		try {
			System.out.println("-------------------------------------");
			System.out.println(context.getBean("sqlSessionFactory"));//빈에 name 을 줘서 불러올 수 있음 설정안할 경우 메소드 이름으로 호출
			System.out.println("-------------------------------------");
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	@Test
	public void testBySqlSessionFactory(){
		try {
			System.out.println("-------------------------------------");
			System.out.println(sessionFactory.toString());
			System.out.println("-------------------------------------");
		}catch (Exception e){
			e.printStackTrace();
		}
	}




}
