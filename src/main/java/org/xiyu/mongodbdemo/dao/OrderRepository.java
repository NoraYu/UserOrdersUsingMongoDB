package org.xiyu.mongodbdemo.dao;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.xiyu.mongodbdemo.entity.OnlineOrder;

import java.util.List;

@Repository
public class OrderRepository {
    @Autowired
    private MongoTemplate template;

    public List<OnlineOrder> findAll(){
        return template.findAll(OnlineOrder.class);
    }

    public OnlineOrder findById(String id){
        return template.findById(id,OnlineOrder.class);
    }

    public OnlineOrder save(OnlineOrder order){

        return template.save(order);
    }

    public void deleteById(String id){
        Query query=new Query();
        query.addCriteria(Criteria.where("orderId").is(id));
        template.remove(query,OnlineOrder.class);
    }

    public OnlineOrder updateById(String id, OnlineOrder order){
        Query query=new Query();
        OnlineOrder o=findById(id);
        o.setItems(order.getItems());
        return template.save(o);
    }
    public List<OnlineOrder> findByUserId(String id){
        Query query=new Query();
        //坑爹，这个query的id一定要$id，不是pojo的变量名userId！！！
        query.addCriteria(Criteria.where("user.$id").is(new ObjectId(id)));
        return template.find(query,OnlineOrder.class);
    }
}
