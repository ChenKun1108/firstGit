package com.pinyougou.search.service.impl;

//Created by  2019/9/15


import com.alibaba.fastjson.JSON;
import com.pinyougou.pojo.TbItem;
import com.pinyougou.search.service.ItemSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.List;

@Component//依赖注入
public class ItemSearchListener implements MessageListener {

    @Autowired
    private ItemSearchService itemSearchService;

    @Override
    public void onMessage(Message message) {
        //转换类型
        TextMessage textMessage = (TextMessage) message;
        //获取消息文本
        try {
            String text = textMessage.getText();
            // TODO Auto-generated catch block
            System.out.println("监听到消息:"+text);
            //json 转换成为list结合
            List<TbItem> itemList = JSON.parseArray(text, TbItem.class);
            //调用searchService接口进行导入
            itemSearchService.importList(itemList);
            // TODO Auto-generated catch block
            System.out.println("导入到solr索引库");
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
