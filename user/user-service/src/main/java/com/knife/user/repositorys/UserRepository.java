package com.knife.user.repositorys;

import com.knife.user.model.User;

/**
 * @author zhou.fan
 * 用户访问数据
 */
public interface UserRepository /*extends JpaRepository<User, Integer>*/ {

    /**
     * 根据用户名称查询
     * @param userName 用户名称
     * @return User
     */
//    @Transactional(readOnly = true ,rollbackFor = Exception.class)
    User findUserByUserName(String userName);

    /**
     * 根据用户名称和密码
     * @param userName 用户名称
     * @param userPass 用户密码
     * @return User
     */
//    @Transactional(readOnly = true ,rollbackFor = Exception.class)
    User findUserByUserNameAndUserPass(String userName, Integer userPass);
}
