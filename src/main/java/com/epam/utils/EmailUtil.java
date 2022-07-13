package com.epam.utils;

import com.epam.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.util.Random;

/**
 * @description: EmailUtil
 * @author: taoz
 * @date: 2022/7/13 10:57
 */
@Component
@Slf4j
public class EmailUtil {

    @Resource
    JavaMailSender mailSender;

    @Resource
    RedisUtil redisUtil;

    @Value("${spring.mail.username}")
    private String mailFrom;

    @Value("${spring.mail.count}")
    private int count;

    public boolean sendMail(String mailTo) {
        MimeMessage message = mailSender.createMimeMessage();
        while (count-- > 0) {
            try {
                MimeMessageHelper helper = new MimeMessageHelper(message, true);
                String codeNum = "";
                int[] code = new int[3];
                Random random = new Random();
                //自动生成验证码
                for (int i = 0; i < 6; i++) {
                    int num = random.nextInt(10) + 48;
                    int uppercase = random.nextInt(26) + 65;
                    int lowercase = random.nextInt(26) + 97;
                    code[0] = num;
                    code[1] = uppercase;
                    code[2] = lowercase;
                    codeNum += (char) code[random.nextInt(3)];
                }
                //标题
                helper.setSubject("您的验证码为：" + codeNum);
                //内容
                helper.setText("您好！，您的验证码为：" + "<h2>" + codeNum + "</h2>" + "千万不能告诉别人哦！", true);
                //邮件接收者
                helper.setTo(mailTo);
                //邮件发送者，必须和配置文件里的一样，不然授权码匹配不上
                helper.setFrom(mailFrom);
                mailSender.send(message);
                redisUtil.set(mailTo, codeNum, 300);
                return true;
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
        return false;
    }

    public boolean verifyCode(String mailTo, String code) {
        String codeOld = redisUtil.get(mailTo).toString();
        if (StringUtils.isBlank(codeOld)) {
            throw new CustomException("verifyCode has expired！");
        }
        if (!code.equals(codeOld)) {
            throw new CustomException("verifyCode is incorrect！");
        }
        return true;
    }

}
