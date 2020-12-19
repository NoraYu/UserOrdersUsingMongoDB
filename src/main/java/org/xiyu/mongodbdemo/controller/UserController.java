package org.xiyu.mongodbdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.xiyu.mongodbdemo.dao.OrderRepository;
import org.xiyu.mongodbdemo.dao.UserRepository;
import org.xiyu.mongodbdemo.entity.OnlineOrder;
import org.xiyu.mongodbdemo.entity.User;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final OrderRepository orderRepository;

    public UserController(UserRepository userRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{userId}")
    public User getByUserId(@PathVariable String userId) {
        return userRepository.findById(userId).orElse(new User());
    }

    @PostMapping("/new")
    public User addNewUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @DeleteMapping("/{userId}")
    public String delete(@PathVariable String userId) {
        userRepository.deleteById(userId);
        return "deleted: " + userId;
    }

    @PutMapping("/{userId}")
    public User update(@PathVariable String userId, @RequestBody User user) {
        User origin=userRepository.findById(userId).orElse(user);
        origin.setName(user.getName());
        origin.setAge(user.getAge());
        return userRepository.save(origin);
    }

    @PostMapping("/{userId}/neworder")
    public OnlineOrder createOder(@PathVariable String userId, @RequestBody OnlineOrder onlineOrder){
        User origin=userRepository.findById(userId).orElse(new User());
        onlineOrder.setUser(origin);
//        List<OnlineOrder> list=origin.getOrders();
        OnlineOrder result=orderRepository.save(onlineOrder);
//        System.out.println("return result: "+result);
//        System.out.println(result.getOrderId());
//        list.add(result);
//        origin.setOrders(list);
        origin.setOrders(result);
        userRepository.save(origin);
        return result;

    }

    @GetMapping("/{userId}/allmyorder")
    public List<OnlineOrder> allMyOrder(@PathVariable String userId){
        return orderRepository.findByUserId(userId);
    }
}
