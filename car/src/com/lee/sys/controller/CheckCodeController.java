package com.lee.sys.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import com.lee.common.Constant;
import com.lee.common.util.WebUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class CheckCodeController {

    @RequestMapping("/sys/checkCode/code")
    public void getCheckCode(HttpServletResponse response) throws IOException {

        //使用hutool 生成验证码
        CircleCaptcha  circleCaptcha  = CaptchaUtil.createCircleCaptcha(120,40,4,4);
        //获取生成验证码字符串
        String code =  circleCaptcha.getCode();
        //把验证码放入session中
        HttpSession session = WebUtil.getSession();
        session.setAttribute(Constant.CHECK_CODE,code);

        //返回响应
        circleCaptcha.write(response.getOutputStream());
    }

}
