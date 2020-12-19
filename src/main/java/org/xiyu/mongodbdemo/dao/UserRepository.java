package org.xiyu.mongodbdemo.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.xiyu.mongodbdemo.entity.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
}
