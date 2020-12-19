package org.xiyu.mongodbdemo.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document
@Data

public class User {
    @Id
    private String userId;
    private String name;
    private Integer age;
    private Date createTime = new Date();
    @JsonIgnore
    @DBRef
    private List<OnlineOrder> orders=new ArrayList<>();

    public void setOrders(OnlineOrder order) {
        if(orders==null){
            synchronized (OnlineOrder.class){
                if (orders==null){
                    orders=new ArrayList<OnlineOrder>();
                }
            }
        }
        this.orders.add(order);
    }


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", createTime=" + createTime +
                '}';
    }
}
