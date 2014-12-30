/**
 * @(#)CrudMapper.java 2014年12月26日
 *
 * Copyright 2008-2014 by Woo Cupid.
 * All rights reserved.
 * 
 */
package com.github.hibatis.mapper;

import java.io.*;

import com.github.hibatis.*;

/**
 * @author Woo Cupid
 * @date 2014年12月26日
 * @version $Revision$
 */
@AutoMapper
public interface CrudMapper<T, PK extends Serializable> extends Mapper<T, PK> {

	// @SelectProvider(type = CrudMapperImpl.class, method = "get")
	T get(PK id);

}
