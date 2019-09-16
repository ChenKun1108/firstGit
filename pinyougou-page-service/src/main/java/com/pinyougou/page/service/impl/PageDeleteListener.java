package com.pinyougou.page.service.impl;

//Created by  2019/9/16


import com.pinyougou.page.service.ItemPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

//删除静态页面
@Component
public class PageDeleteListener implements MessageListener {

    @Autowired
    private ItemPageService itemPageService;

    @Override
    public void onMessage(Message message) {
        //转换格式
        ObjectMessage objectMessage = (ObjectMessage) message;
        //获取消息
        try {
            Long[] goodsIds= (Long[]) objectMessage.getObject();
            System.out.println("ItemDeleteListener 监听接收到消息..."+goodsIds);
            boolean deleteItemHtml = itemPageService.deleteItemHtml(goodsIds);
            System.out.println("网页删除结果："+deleteItemHtml);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
