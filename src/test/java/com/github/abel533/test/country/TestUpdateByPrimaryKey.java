package com.github.abel533.test.country;

import com.github.abel533.mapper.CountryMapper;
import com.github.abel533.mapper.MybatisHelper;
import com.github.abel533.model.Country;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

/**
 * 通过PK更新实体类全部属性
 *
 * @author liuzh
 */
public class TestUpdateByPrimaryKey {

    /**
     * 更新0个
     */
    @Test
    public void testDynamicUpdateByPrimaryKeyAll() {
        SqlSession sqlSession = MybatisHelper.getSqlSession();
        try {
            CountryMapper mapper = sqlSession.getMapper(CountryMapper.class);
            Assert.assertEquals(0, mapper.updateByPrimaryKey(new Country()));
        } finally {
            sqlSession.close();
        }
    }

    /**
     * 入参为null时更新全部
     */
    @Test
    public void testDynamicUpdateByPrimaryKeyAllByNull() {
        SqlSession sqlSession = MybatisHelper.getSqlSession();
        try {
            CountryMapper mapper = sqlSession.getMapper(CountryMapper.class);
            mapper.updateByPrimaryKey(null);
        } finally {
            sqlSession.close();
        }
    }

    /**
     * 根据查询条件进行查询
     */
    @Test
    public void testDynamicUpdateByPrimaryKey() {
        SqlSession sqlSession = MybatisHelper.getSqlSession();
        try {
            CountryMapper mapper = sqlSession.getMapper(CountryMapper.class);
            Country country = new Country();
            country.setId(174);
            country.setCountryname("美国");
            Assert.assertEquals(1, mapper.updateByPrimaryKey(country));

            country = mapper.selectByPrimaryKey(174);
            Assert.assertNotNull(country);
            Assert.assertEquals(174, (int) country.getId());
            Assert.assertEquals("美国",country.getCountryname());
            Assert.assertNull(country.getCountrycode());
        } finally {
            sqlSession.close();
        }
    }

    class Key extends Country {
        private String countrytel;

        public String getCountrytel() {
            return countrytel;
        }

        public void setCountrytel(String countrytel) {
            this.countrytel = countrytel;
        }
    }

    /**
     * 继承类可以使用,但多出来的属性无效
     */
    @Test
    public void testDynamicUpdateByPrimaryKeyNotFoundKeyProperties() {
        SqlSession sqlSession = MybatisHelper.getSqlSession();
        try {
            CountryMapper mapper = sqlSession.getMapper(CountryMapper.class);

           Assert.assertEquals(0, mapper.updateByPrimaryKey(new Key()));

            Key key = new Key();
            key.setId(174);
            key.setCountrycode("CN");
            key.setCountrytel("+86");
            Assert.assertEquals(1, mapper.updateByPrimaryKey(key));
        } finally {
            sqlSession.close();
        }
    }

}
