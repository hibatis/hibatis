/**
 * @(#)MappedStatementHolder.java 2014年12月29日
 *
 * Copyright 2008-2014 by Woo Cupid.
 * All rights reserved.
 * 
 */
package com.github.hibatis;

import java.util.*;

import org.apache.ibatis.mapping.*;

/**
 * @author Woo Cupid
 * @date 2014年12月29日
 * @version $Revision$
 */
public class MappedStatementHolder {

	private static Map<String, MappedStatement> holder = new HashMap<String, MappedStatement>();

	public static void addMappedStatement(String name, MappedStatement ms) {
		holder.put(name, ms);
	}

	public static MappedStatement getMappedStatement(String name) {
		return holder.get(name);
	}

}
