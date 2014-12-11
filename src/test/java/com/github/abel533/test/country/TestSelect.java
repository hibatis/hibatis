package com.github.abel533.test.country;

import com.github.abel533.mapper.CountryMapper;
import com.github.abel533.mapper.MybatisHelper;
import com.github.abel533.model.Country;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * 通过实体类属性进行查询
 *
 * @author liuzh
 */
public class TestSelect {

    /**
     * 查询全部
     */
    @Test
    public void testDynamicSelectAll() {
        SqlSession sqlSession = MybatisHelper.getSqlSession();
        try {
            CountryMapper mapper = sqlSession.getMapper(CountryMapper.class);
            List<Country> countryList = mapper.select(new Country());
            //查询总数
            Assert.assertEquals(183, countryList.size());
        } finally {
            sqlSession.close();
        }
    }

    /**
     * 查询全部
     */
    @Test
    public void testDynamicSelectPage() {
        SqlSession sqlSession = MybatisHelper.getSqlSession();
        try {
            CountryMapper mapper = sqlSession.getMapper(CountryMapper.class);
            Country country = new Country();
            country.setCountrycode("US");
            List<Country> countryList = mapper.selectPage(country, 0, 10);
            //查询总数
            Assert.assertEquals(1, countryList.size());

            countryList = mapper.selectPage(null, 100, 10);
            //查询总数
            Assert.assertEquals(10, countryList.size());
        } finally {
            sqlSession.close();
        }
    }

    /**
     * 入参为null时查询全部
     */
    @Test
    public void testDynamicSelectAllByNull() {
        SqlSession sqlSession = MybatisHelper.getSqlSession();
        try {
            CountryMapper mapper = sqlSession.getMapper(CountryMapper.class);
            mapper.select(null);
        } finally {
            sqlSession.close();
        }
    }

    /**
     * 根据查询条件进行查询
     */
    @Test
    public void testDynamicSelect() {
        SqlSession sqlSession = MybatisHelper.getSqlSession();
        try {
            CountryMapper mapper = sqlSession.getMapper(CountryMapper.class);
            Country country = new Country();
            country.setCountrycode("CN");
            List<Country> countryList = mapper.select(country);

            Assert.assertEquals(1, countryList.size());
            Assert.assertEquals(true, countryList.get(0).getId() == 35);
            Assert.assertEquals("China", countryList.get(0).getCountryname());
        } finally {
            sqlSession.close();
        }
    }

    /**
     * 查询不存在的结果
     */
    @Test
    public void testDynamicSelectZero() {
        SqlSession sqlSession = MybatisHelper.getSqlSession();
        try {
            CountryMapper mapper = sqlSession.getMapper(CountryMapper.class);
            Country country = new Country();
            country.setCountrycode("CN");
            country.setCountryname("天朝");//实际上是 China
            List<Country> countryList = mapper.select(country);

            Assert.assertEquals(0, countryList.size());
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
    public void testDynamicSelectNotFoundKeyProperties() {
        SqlSession sqlSession = MybatisHelper.getSqlSession();
        try {
            CountryMapper mapper = sqlSession.getMapper(CountryMapper.class);
            //根据主键删除
            Assert.assertEquals(183, mapper.select(new Key()).size());

            Key key = new Key();
            key.setCountrycode("CN");
            key.setCountrytel("+86");
            Assert.assertEquals(1, mapper.select(key).size());
        } finally {
            sqlSession.close();
        }
    }

}
