/**
 * @(#)CrudMapperImpl.java 2014年12月29日
 *
 * Copyright 2008-2014 by Woo Cupid.
 * All rights reserved.
 * 
 */
package com.github.hibatis.mapper;

import java.io.*;

import org.apache.ibatis.scripting.xmltags.*;

/**
 * @author Woo Cupid
 * @date 2014年12月29日
 * @version $Revision$
 */
public class CrudMapperImpl {

	public SqlNode get(Serializable id) {
		// Class<?> entityClass = getSelectReturnType(ms);
		// // 获取主键字段映射
		// List<ParameterMapping> parameterMappings = getPrimaryKeyParameterMappings(ms);
		// // 开始拼sql
		// BEGIN();
		// // select全部列
		// SELECT(EntityHelper.getSelectColumns(entityClass));
		// // from表
		// FROM(tableName(entityClass));
		// // where条件，主键字段=#{property}
		// WHERE(EntityHelper.getPrimaryKeyWhere(entityClass));
		// // SQL()方法获取最终SQL，使用静态SqlSource
		// StaticSqlSource sqlSource = new StaticSqlSource(ms.getConfiguration(), SQL(), parameterMappings);
		// // 替换原有的SqlSource
		// setSqlSource(ms, sqlSource);
		// // 将返回值修改为实体类型
		// setResultType(ms, entityClass);

		return null;
	}

}
