package com.github.hibatis;

import java.io.*;
import java.sql.*;

import org.apache.ibatis.io.*;
import org.apache.ibatis.jdbc.*;
import org.apache.ibatis.session.*;

import com.github.hibatis.*;

/**
 * Description: MybatisHelper
 * Author: liuzh
 * Update: liuzh(2014-06-06 13:33)
 */
public class MybatisHelper {

	private static SqlSessionFactory sqlSessionFactory;

	static {
		try {
			// 创建SqlSessionFactory
			Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
			sqlSessionFactory = new HibatisSqlSessionFactoryBuilder().build(reader);
			reader.close();
			// 创建数据库
			SqlSession session = null;
			try {
				session = sqlSessionFactory.openSession();
				Connection conn = session.getConnection();
				reader = Resources.getResourceAsReader("CreateDB.sql");
				ScriptRunner runner = new ScriptRunner(conn);
				runner.setLogWriter(null);
				runner.runScript(reader);
				reader.close();
			} finally {
				if (session != null) {
					session.close();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取Session
	 * 
	 * @return
	 */
	public static SqlSession getSqlSession() {
		return sqlSessionFactory.openSession();
	}
}
