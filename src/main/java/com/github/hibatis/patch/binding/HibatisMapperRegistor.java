/**
 * @(#)MapperRegistor.java 2014年12月29日
 *
 * Copyright 2008-2014 by Woo Cupid.
 * All rights reserved.
 * 
 */
package com.github.hibatis.patch.binding;

import java.util.*;

import org.apache.ibatis.binding.*;
import org.apache.ibatis.builder.annotation.*;
import org.apache.ibatis.session.*;

/**
 * Don't know why most of the open source coder set the field to private and doesn't provide a getter/setter. ;(
 * 
 * @author Woo Cupid
 * @date 2014年12月29日
 * @version $Revision$
 */
public class HibatisMapperRegistor extends MapperRegistry {

	protected Configuration config;
	protected Map<Class<?>, MapperProxyFactory<?>> knownMappers = new HashMap<Class<?>, MapperProxyFactory<?>>();

	public HibatisMapperRegistor(Configuration config) {
		super(config);
	}

	@Override
	public <T> void addMapper(Class<T> type) {
		if (type.isInterface()) {
			if (hasMapper(type)) {
				throw new BindingException("Type " + type + " is already known to the MapperRegistry.");
			}
			boolean loadCompleted = false;
			try {
				knownMappers.put(type, new MapperProxyFactory<T>(type));
				// It's important that the type is added before the parser is run
				// otherwise the binding may automatically be attempted by the
				// mapper parser. If the type is already known, it won't try.

				// Patch, replace MapperAnnotatioBuilder
				MapperAnnotationBuilder parser = new MapperAnnotationBuilder(config, type);
				parser.parse();
				loadCompleted = true;
			} finally {
				if (!loadCompleted) {
					knownMappers.remove(type);
				}
			}
		}
	}

}
