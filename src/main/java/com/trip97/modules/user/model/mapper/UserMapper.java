package com.trip97.modules.user.model.mapper;

import com.trip97.modules.user.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {

    Optional<User> findByEmail(String email);
    Integer save(User user);
    List<User> findAll();
}
