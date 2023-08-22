package com.kuang.dao;

import com.kuang.pojo.User;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

public class UserDaoImpl extends SqlSessionDaoSupport implements UserMapper {

    //sqlSessoin 不是我们自己创建了，Spring来管理
    /*private SqlSessionTemplate sqlSession;

    public void setSqlSession(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }*/

    @Override
    public List<User> selectUser() {
        UserMapper mapper = super.getSqlSessionTemplate().getMapper(UserMapper.class);
        return mapper.selectUser();
    }
}
