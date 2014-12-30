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
				SqlSource sqlSource = ms.getSqlSource();
				// if (sqlSource instanceof ProviderSqlSource) {
				// ExProvidedSqlSource ps = new ExProvidedSqlSource((ProviderSqlSource) sqlSource, ms);
				// ReflectionUtils.setFieldValue(ms, "sqlSource", ps);
				// }
				MappedStatementHolder.addMappedStatement(ms.getId(), ms);
			}
		}
		return super.build(config);
	}

}
