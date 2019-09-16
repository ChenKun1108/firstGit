package com.pinyougou.search.service.impl;

//Created by  2019/9/15


import com.pinyougou.search.service.ItemSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import java.util.Arrays;

//用于删除索引库中记录
@Component
public class ItemDeleteListener implements MessageListener{

    @Autowired
    private ItemSearchService itemSearchService;

    @Override
    public void onMessage(Message message) {
        //转换类型
        ObjectMessage objectMessage = (ObjectMessage) message;
        //获取文本信息
        try {
            Long[] ids = (Long[]) objectMessage.getObject();
            System.out.println("监听到消息:"+ids);
            itemSearchService.deleteByGoodsIds(Arrays.asList(ids));
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
