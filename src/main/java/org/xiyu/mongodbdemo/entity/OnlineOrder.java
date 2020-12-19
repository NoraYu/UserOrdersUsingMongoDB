package org.xiyu.mongodbdemo.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class OnlineOrder {
    @Id
    private String orderId;
    private String items;
    @DBRef
    private User user;
//一定要重写toString方法，不然会死循环
    @Override
    public String toString() {
        return "OnlineOrder{" +
                "items='" + items + '\'' +
                ", user=" + user +
                '}';
    }
}
