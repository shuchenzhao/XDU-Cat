package com.web.xducatserver.Repository;

import com.web.xducatserver.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;


public interface UserRepository extends JpaRepository<User,String> {
    @Query(value="select * from user where username=?1",nativeQuery=true)
    public User findUserByUsername(String username);//寻找用户名对应的用户

    @Modifying
    @Transactional
    @Query(value="insert into user value(?1,?2)",nativeQuery=true)
    public void addManager(String username, String password);//添加新的管理员

    @Modifying
    @Transactional
    @Query(value="update user set password=?2 where username=?1",nativeQuery=true)
    public void modifyPassword(String username,  String nowpassword);//修改管理员密码
}
