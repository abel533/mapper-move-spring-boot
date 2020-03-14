package io.mybatis.demo.service.impl;

import io.mybatis.demo.custom.seq.Direction;
import io.mybatis.demo.custom.seq.MoveMapperUtil;
import io.mybatis.demo.mapper.UserMapper;
import io.mybatis.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.mybatis.demo.service.UserService;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

    @Override
    public List<User> getAll() {
        return userMapper.selectAll();
    }

    @Override
    public int move(Long movingId, Long targetId, boolean isBefore) {
        return MoveMapperUtil.move(userMapper, movingId, targetId, isBefore ? Direction.BEFORE: Direction.AFTER, Collections.emptyMap());
    }

}
