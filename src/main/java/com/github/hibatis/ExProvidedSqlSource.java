/**
 * @(#)ExProvidedSqlSource.java 2014年12月29日
 *
 * Copyright 2008-2014 by Woo Cupid.
 * All rights reserved.
 * 
 */
package com.github.hibatis;

import java.lang.reflect.*;
import java.util.*;

import org.apache.ibatis.builder.*;
import org.apache.ibatis.builder.annotation.*;
import org.apache.ibatis.mapping.*;
import org.apache.ibatis.scripting.xmltags.*;

/**
 * Due to ProviderSqlSource doesn't provide any getter method.
 * 
 * So, just new create a customer ProviderSqlSource.
 * 
 * @author Woo Cupid
 * @date 2014年12月29日
 * @version $Revision$
 */
public class ExProvidedSqlSource implements SqlSource {

	protected ProviderSqlSource original;
	protected MappedStatement mappedStatement;

	/**
	 * @param sqlSource
	 * @param ms
	 */
	public ExProvidedSqlSource(ProviderSqlSource original, MappedStatement ms) {
		this.original = original;
		this.mappedStatement = ms;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.ibatis.mapping.SqlSource#getBoundSql(java.lang.Object)
	 */
	@Override
	public BoundSql getBoundSql(Object parameterObject) {
		SqlSource sqlSource = createSqlSource(parameterObject);
		return sqlSource.getBoundSql(parameterObject);
	}

	private SqlSource createSqlSource(Object param) {
		SqlSourceBuilder parser = (SqlSourceBuilder) ReflectionUtils.getFieldValue(original, "sqlSourceParser");
		Class<?> type = (Class<?>) ReflectionUtils.getFieldValue(original, "providerType");
		Method method = (Method) ReflectionUtils.getFieldValue(original, "providerMethod");

		boolean providerTakesParameterObject = (boolean) ReflectionUtils.getFieldValue(original,
				"providerTakesParameterObject");
		try {

			List<Object> context = new ArrayList<Object>();
			context.add(param);
			context.add(this.mappedStatement);
			List<Object> args = context.subList(0, method.getParameterTypes().length);

			Class<?> returnType = method.getReturnType();
			Object result = method.invoke(this, args);
			if (result instanceof Void) {

			} else if (result instanceof String) {

			} else if (result instanceof SqlNode) {
				// SqlNode sqlNode = (SqlNode) method.invoke(this, args);
				// DynamicSqlSource dynamicSqlSource = new DynamicSqlSource(ms.getConfiguration(), sqlNode);
				// setSqlSource(ms, dynamicSqlSource);
			} else {
				throw new RuntimeException("自定义Mapper方法返回类型错误,可选的返回类型为void和SqlNode!");
			}

			Class<?> parameterType = param == null ? Object.class : param.getClass();
			// return parser.parse(sql, parameterType, new HashMap<String, Object>());

			return null;
		} catch (Exception e) {
			throw new BuilderException("Error invoking SqlProvider method (" + type.getName() + "." + method.getName()
					+ ").  Cause: " + e, e);
		}
	}

	public static void main(String[] args) {
		List<Object> context = new ArrayList<Object>();
		context.add(1);
		context.add(2);
	}
}
