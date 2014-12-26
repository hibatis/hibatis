/**
 * @(#)HibatisSqlSessionFactoryBuilder.java 2014年12月26日
 *
 * Copyright 2008-2014 by Woo Cupid.
 * All rights reserved.
 * 
 */
package com.github.hibatis;

import java.util.*;

import org.apache.ibatis.mapping.*;
import org.apache.ibatis.session.*;
import org.apache.log4j.*;

/**
 * @author Woo Cupid
 * @date 2014年12月26日
 * @version $Revision$
 */
public class HibatisSqlSessionFactoryBuilder extends SqlSessionFactoryBuilder {

	private static Logger logger = Logger.getLogger(HibatisSqlSessionFactoryBuilder.class.getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.ibatis.session.SqlSessionFactoryBuilder#build(org.apache.ibatis.session.Configuration)
	 */
	@Override
	public SqlSessionFactory build(Configuration config) {

		Collection<MappedStatement> mappedStatements = config.getMappedStatements();
		for (Object obj : mappedStatements) {
			if (obj instanceof MappedStatement) {
				MappedStatement ms = (MappedStatement) obj;
				logger.info(ms.getId());
			}
		}

		return super.build(config);
	}

	/**
	 * 根据msId获取接口类
	 *
	 * @param msId
	 * @return
	 * @throws ClassNotFoundException
	 */
	public Class<?> getMapperClass(String msId) {
		String mapperClassStr = msId.substring(0, msId.lastIndexOf("."));
		try {
			return Class.forName(mapperClassStr);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("无法获取Mapper接口信息:" + msId);
		}
	}

	/**
	 * 获取执行的方法名
	 *
	 * @param ms
	 * @return
	 */
	public String getMethodName(String msId) {
		return msId.substring(msId.lastIndexOf(".") + 1);
	}
}
