package com.pinyougou.page.service.impl;

//Created by  2019/9/15


import com.pinyougou.page.service.ItemPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

//生成页面服务消息消费者监听器
@Component
public class PageListener implements MessageListener{

    @Autowired
    private ItemPageService itemPageService;

    @Override
    public void onMessage(Message message) {
        //转换类型
        TextMessage textMessage = (TextMessage) message;
        //获取文本消息
        try {
            String text = textMessage.getText();
            System.out.println("PageListener收到监听消息"+text);
            itemPageService.genItemHtml(Long.parseLong(text));
        } catch (JMSException e) {
            System.out.println("PageListener收到监听消息异常");
        }
    }
}
