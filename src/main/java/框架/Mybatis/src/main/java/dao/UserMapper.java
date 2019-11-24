package dao;

import domain.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {
//    根据id查询实体
    User selectByid2(Integer id);
//    查询全部
List<User> selectAllUser();
//增加数据
Integer insertByUser(User user);

//编辑User
    void editByUserId(User user);

    //Map传参
    List<User> selectByMap(Map<String,Object> map);

}
