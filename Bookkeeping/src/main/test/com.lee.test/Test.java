package com.lee.test;

import com.lee.ache.BillsTypeCache;
import com.lee.mapper.SysUserMapper;

import com.lee.service.BillTypeService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

    //测试spring-dao
    @org.junit.Test
    public void testSpringdao() throws Exception {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-context.xml");
        SqlSessionFactoryBean bean = applicationContext.getBean(SqlSessionFactoryBean.class);
        //        MapperScannerConfigurer bean = applicationContext.getBean(MapperScannerConfigurer.class);
        System.out.println(bean);
        SqlSessionFactory object = bean.getObject();
        SqlSession sqlSession = object.openSession();
        SysUserMapper mapper = sqlSession.getMapper(SysUserMapper.class);
        System.out.println(mapper.selectByPrimaryKey(1));
    }

    @org.junit.Test
    public void testAop() {


        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        BillTypeService bean = context.getBean(BillTypeService.class);
        System.out.println(bean.selectByPrimaryKey(1));
        //bean.select();
    }
}


