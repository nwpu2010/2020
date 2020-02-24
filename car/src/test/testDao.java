package test;

import com.lee.bussi.mapper.BussiCustomerMapper;
import com.lee.bussi.pojo.BussiCustomer;
import com.lee.bussi.vo.BussiCustVO;
import com.lee.sys.mapper.SysUsersMapper;
import com.lee.sys.vo.SysUsersVO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class testDao extends BaseTest {

    @Autowired
    BussiCustomerMapper bussiCustomerMapper;

    @Autowired
    SysUsersMapper sysUsersMapper;

    @Test
    public  void test(){


        SysUsersVO sysUsersVO = sysUsersMapper.checkLogin("12", "12");
        System.out.println(sysUsersVO.toString());

    }

}
