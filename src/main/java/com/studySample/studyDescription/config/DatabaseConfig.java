package com.studySample.studyDescription.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration //자바 기반의 설정파일로 인식하도록 함, xml 설정 바익을 JAVA 로 대체
@PropertySource("classpath:/application.properties")    //경로 지정, DB 설정 등 KEY - VALUE 값을 가진 설정값을 가져오는 기능
public class DatabaseConfig {

    @Autowired  //BEAN 으로 등록된 인스턴스를 클래스에 주입하는데 사용
    private ApplicationContext context;
    //ApplicationContext BEAN 의 생성과 사용, 관계, 생명주기 등을 관리,
    //스프링 컨테이너중 하나이며 컨테이너에 BEAN 을 주입하는 방식으로 의존성 문제 해결


    @Bean   //@Configuration 선언 메서드 레벨에서만 선언 가능, 해당 객체는 스프링 컨테이너에 관리되는 BEAN 으로 등록
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public HikariConfig hikariConfig() {
    /*
    @ConfigurationProperties 어노테이션은 prefix 속성 선언, prefix 는 접두사를 의미하며 위에서 @PropertySource 에서 선언한
    설정중 해당 "spring.datasource.hikari" 로 시작하는 설정을 모두 불러와서 선언 가능
    */
        return new HikariConfig();
        //히카리 CP 객체 생성, 히카리CP 는 커넥션 풀 라이브러리
    }

    @Bean
    public DataSource dataSource() {    //커넥션 풀을 지원하기 위한 인터페이스
        return new HikariDataSource(hikariConfig());
    }

    @Bean   //(name = "abc") Bean 에 name 지정이 가능함
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        //SqlSessionFactory 은 DB커넥션과 SQL실행에 관한 모든것을 갖는 객체
        //FactoryBean 인터페이스의 구현 클래스로 마이바티스와 스프링의 연동 모듈로 사용
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();    //FactoryBean 객체 생성
        factoryBean.setDataSource(dataSource());    //생성한 객체에 dataSource 세팅 (DB 설정정보 주입)
		factoryBean.setMapperLocations(context.getResources("classpath:/mappers/**/*Mapper.xml")); //mybatis 파일 불러올 수 있도록 설정
        factoryBean.setConfiguration(mybatisConfig());
        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSession() throws Exception {
        //SqlSessionTemplate 은 DB의 커밋, 롤백 등 SQL 실행에 필요한 모든 메서드를 갖는 객체
        return new SqlSessionTemplate(sqlSessionFactory());
    }

    @Bean
    @ConfigurationProperties(prefix = "mybatis.configuration")  //mybatis.configuration 로 시작하는 모든 설정을읽음
    public org.apache.ibatis.session.Configuration mybatisConfig(){
        return new org.apache.ibatis.session.Configuration();
    }

}