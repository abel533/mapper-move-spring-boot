package io.mybatis.demo.mapper;

import io.mybatis.demo.custom.seq.MoveMapper;
import io.mybatis.demo.model.User;
import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User>, MoveMapper<User> {

}
