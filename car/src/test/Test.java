package test;

import com.lee.common.base.Query;
import com.lee.sys.mapper.SysUsersMapper;
import com.lee.sys.pojo.SysUsers;
import com.lee.sys.vo.SysUsersVO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Test {

    public static void main(String[] args) {
       ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        SysUsersMapper mapper  = context.getBean(SysUsersMapper.class);
        SysUsers sysUsers1 = new SysUsers();
        sysUsers1.setId(2);


        List<SysUsersVO> sysUsersVOS = mapper.selectList(new Query());

        System.out.println(sysUsersVOS);


    }

    @org.junit.Test
   public void testLastIndexOf(){
        String  string  = "1djk";
        System.out.println(string.lastIndexOf("1"));//1的前面后多少个
        System.out.println(string.lastIndexOf("d"));//d的前面有多少个
        System.out.println(string.indexOf("d"));//d的前面有多少个

    }
}
