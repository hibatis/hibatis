package com.github.hibatis.test;

import org.apache.ibatis.session.*;
import org.junit.*;

import com.github.hibatis.*;
import com.github.hibatis.mapper.*;
import com.github.hibatis.modal.*;

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
	public void testGet() {
		SqlSession sqlSession = MybatisHelper.getSqlSession();
		try {
			CountryMapper mapper = sqlSession.getMapper(CountryMapper.class);
			Country country = mapper.get(1);
			System.out.println(country);
		} finally {
			sqlSession.close();
		}
	}

}
