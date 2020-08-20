package com.example.business.common.controller;

import com.example.business.common.contants.MyContants;
import com.example.business.common.entity.CaptchaCode;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 验证码
 */
@RestController
public class CaptchaController {

    @Resource
    private DefaultKaptcha defaultKaptcha;

    @RequestMapping(value = "/captcha",method = RequestMethod.GET)
    public void captcha(HttpSession session, HttpServletResponse response) {

        response.setDateHeader("Expires",0);
        response.setHeader("Cache-Control","no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control","post-check=0, pre-check=0");
        response.setHeader("Pragma","no-cache");
        response.setContentType("image/jpeg");

        String text = defaultKaptcha.createText();
        //60秒内有效
        session.setAttribute(MyContants.CAPTCHA_SESSION_KEY,new CaptchaCode(text,90));

        ServletOutputStream outputStream = null;
        try {
            BufferedImage image = defaultKaptcha.createImage(text);
            outputStream = response.getOutputStream();
            ImageIO.write(image,"jpg",outputStream);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
