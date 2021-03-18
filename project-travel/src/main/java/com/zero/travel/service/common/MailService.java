package com.zero.travel.service.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.internet.MimeMessage;
import java.util.Map;

/**
 * 邮件服务
 * @author LJC
 * @version 1.0
 * @date 2021/3/17 13:54
 */
@Service
public class MailService {

    private static final Logger log = LoggerFactory.getLogger(MailService.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Autowired
    private Environment ev;


    /**
     * 发送HTML模板邮件
     * @param subject
     * @param content
     * @param tos
     * @throws Exception
     */
    public void sendHtmlMail(String subject, String content, String... tos) throws Exception {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,true,"UTF-8");

        messageHelper.setFrom(ev.getProperty("spring.mail.username"));
        messageHelper.setTo(tos);
        messageHelper.setSubject(subject);
        messageHelper.setText(content,true);


        javaMailSender.send(mimeMessage);
        log.info("Email sent success!");
    }

    /**
     * 渲染模板
     * @param templateFile
     * @param paramMap  LocaleContextHolder.getLocale()
     * @return
     */
    public String renderTemplate(final String templateFile, Map<String,Object> paramMap){
        Context context=new Context();

        context.setVariables(paramMap);

        return templateEngine.process(templateFile,context);
    }
}
