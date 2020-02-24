package com.lee.common.util;

import com.lee.common.Constant;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class WebUtil {
    //获取 HttpServletRequest
   public static  HttpServletRequest getRequst(){

       ServletRequestAttributes requestAttributes =  (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
       HttpServletRequest request = requestAttributes.getRequest();
        return  request;


   }

   //获取当前session
    public  static HttpSession  getSession(){

       return  getRequst().getSession();
    }

    //获取当前servletContext

    public  static ServletContext  getServletContext(){

       return ContextLoader.getCurrentWebApplicationContext().getServletContext();
    }


    /**
     *  获取当前登录用户
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T  getCurrentUser(Class<T> cls){
        Object obj = getSession().getAttribute(Constant.CURRENT_USER);
        if(obj != null){
            return  (T) obj;
        }
        return  null;
    };

    /**
     *  将当前用户放入session
     * @param user
     */
    public static void  setCurrentUser(Object user){
        getSession().setAttribute(Constant.CURRENT_USER,user);
    };

    /**
     *  获取当前验证码
     * @return
     */
    public static String  getCheckCode(){
        Object obj = getSession().getAttribute(Constant.CHECK_CODE);
        if (obj != null){
            return  obj.toString();
        }
        return "";
    };

    /**
     *  将验证码放入session
     * @param code
     */
    public static void  setCheckCode(String code){
        getSession().setAttribute(Constant.CHECK_CODE,code);
    };

}
