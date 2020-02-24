package com.lee.ache;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Aspect
//<!--不使用这个注解，需要在配置文件spring-context.xml中加<aop:aspectj-autoproxy></aop:aspectj-autoproxy>-->
@EnableAspectJAutoProxy
public class BillsTypeCache {

    /*
 切点
  */
    public static final String POINT_CUT = "execution(* com.lee.service.BillTypeService.selectByPrimaryKey(..))";
    /**
     * 联表查询字段名
     */
    public static final String COLUMN = "typeid";
    /*
    保存从数据查询的字段结果
     */
    private static Map<String, Object> billNameCache = new HashMap<>();

    @Around(POINT_CUT)
    public Object saveCache(ProceedingJoinPoint pj) {
        Integer arg = (Integer) pj.getArgs()[0];
        System.err.println("aop==============================================");
        Object obj = billNameCache.get(COLUMN + arg);
        //没有缓存就查表
        if (obj == null) {
            try {
                    /*
                注意：缓存单个id查询结果，比缓存所有更节约存储空间，
                    */
                    obj = pj.proceed();
                billNameCache.put(COLUMN + arg, obj);

            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }

        System.err.println("aop==============================================");
        return obj;
    }
}
