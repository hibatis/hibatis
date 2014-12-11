package com.github.abel533.test.user;

import com.github.abel533.mapper.MybatisHelper;
import com.github.abel533.mapper.UserLoginMapper;
import com.github.abel533.model.UserLogin;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 通过主键删除
 *
 * @author liuzh
 */
public class TestUserLogin {


    /**
     * 新增
     */
    @Test
    public void testInsert() {
        SqlSession sqlSession = MybatisHelper.getSqlSession();
        try {
            UserLoginMapper mapper = sqlSession.getMapper(UserLoginMapper.class);

            UserLogin userLogin = new UserLogin();
            userLogin.setUsername("abel533");
            userLogin.setLogindate(new Date());
            userLogin.setLoginip("192.168.123.1");

            Assert.assertEquals(1, mapper.insert(userLogin));

            Assert.assertNotNull(userLogin.getLogid());
            Assert.assertTrue(userLogin.getLogid() > 10);
            //这里测了实体类入参的删除
            Assert.assertEquals(1, mapper.deleteByPrimaryKey(userLogin));
        } finally {
            sqlSession.close();
        }
    }

    /**
     * 主要测试删除
     */
    @Test
    public void testDelete() {
        SqlSession sqlSession = MybatisHelper.getSqlSession();
        try {
            UserLoginMapper mapper = sqlSession.getMapper(UserLoginMapper.class);
            //查询总数
            Assert.assertEquals(10, mapper.selectCount(new UserLogin()));
            //根据主键查询
            Map<String, Object> key = new HashMap<String, Object>();
            key.put("logid", 1);
            key.put("username", "test1");
            UserLogin userLogin = mapper.selectByPrimaryKey(key);
            //根据主键删除
            Assert.assertEquals(1, mapper.deleteByPrimaryKey(key));

            //查询总数
            Assert.assertEquals(9, mapper.selectCount(new UserLogin()));
            //插入
            Assert.assertEquals(1, mapper.insert(userLogin));
        } finally {
            sqlSession.close();
        }
    }


    /**
     * 查询
     */
    @Test
    public void testSelect() {
        SqlSession sqlSession = MybatisHelper.getSqlSession();
        try {
            UserLoginMapper mapper = sqlSession.getMapper(UserLoginMapper.class);
            UserLogin userLogin = new UserLogin();
            userLogin.setUsername("test1");
            List<UserLogin> userLogins = mapper.select(userLogin);
            Assert.assertEquals(5, userLogins.size());
        } finally {
            sqlSession.close();
        }
    }

    /**
     * 根据主键全更新
     */
    @Test
    public void testUpdateByPrimaryKey() {
        SqlSession sqlSession = MybatisHelper.getSqlSession();
        try {
            UserLoginMapper mapper = sqlSession.getMapper(UserLoginMapper.class);
            Map<String, Object> key = new HashMap<String, Object>();
            key.put("logid", 2);
            key.put("username", "test1");
            UserLogin userLogin = mapper.selectByPrimaryKey(key);
            Assert.assertNotNull(userLogin);
            userLogin.setLoginip("1.1.1.1");
            userLogin.setLogindate(null);
            //不会更新username
            Assert.assertEquals(1, mapper.updateByPrimaryKey(userLogin));

            userLogin = mapper.selectByPrimaryKey(userLogin);
            Assert.assertNull(userLogin.getLogindate());
            Assert.assertEquals("1.1.1.1", userLogin.getLoginip());
        } finally {
            sqlSession.close();
        }
    }

    /**
     * 根据主键更新非null
     */
    @Test
    public void testUpdateByPrimaryKeySelective() {
        SqlSession sqlSession = MybatisHelper.getSqlSession();
        try {
            UserLoginMapper mapper = sqlSession.getMapper(UserLoginMapper.class);

            Map<String, Object> key = new HashMap<String, Object>();
            key.put("logid", 1);
            key.put("username", "test1");

            UserLogin userLogin = mapper.selectByPrimaryKey(key);
            Assert.assertNotNull(userLogin);
            userLogin.setLogindate(null);
            userLogin.setLoginip("1.1.1.1");
            //不会更新username
            Assert.assertEquals(1, mapper.updateByPrimaryKeySelective(userLogin));

            userLogin = mapper.selectByPrimaryKey(key);
            Assert.assertNotNull(userLogin.getLogindate());
            Assert.assertEquals("1.1.1.1", userLogin.getLoginip());
        } finally {
            sqlSession.close();
        }
    }


}
