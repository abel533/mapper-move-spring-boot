package io.mybatis.demo.service;


import io.mybatis.demo.model.User;

import java.util.List;

public interface UserService {
	/**
	 * 获取所有
	 *
	 * @return
	 */
	List<User> getAll();

	/**
	 * 移动节点顺序
	 *
	 * @param movingId
	 * @param targetId
	 * @param isBefore 目标节点的上方true or 下面false
	 * @return
	 */
	int move(Long movingId, Long targetId, boolean isBefore);
}
