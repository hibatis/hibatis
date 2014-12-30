///**
// * @(#)HibatisMapperAnnotationBuilder.java 2014年12月29日
// *
// * Copyright 2008-2014 by Woo Cupid.
// * All rights reserved.
// * 
// */
//package com.github.hibatis.patch.builder.annotation;
//
//import java.lang.annotation.*;
//import java.lang.reflect.*;
//import java.util.*;
//
//import org.apache.ibatis.binding.*;
//import org.apache.ibatis.builder.*;
//import org.apache.ibatis.builder.annotation.*;
//import org.apache.ibatis.mapping.*;
//import org.apache.ibatis.scripting.*;
//import org.apache.ibatis.session.*;
//
///**
// * @author Woo Cupid
// * @date 2014年12月29日
// * @version $Revision$
// */
//public class HibatisMapperAnnotationBuilder extends MapperAnnotationBuilder {
//
//	protected final Set<Class<? extends Annotation>> sqlAnnotationTypes = new HashSet<Class<? extends Annotation>>();
//	protected final Set<Class<? extends Annotation>> sqlProviderAnnotationTypes = new HashSet<Class<? extends Annotation>>();
//
//	/**
//	 * @param configuration
//	 * @param type
//	 */
//	public HibatisMapperAnnotationBuilder(Configuration configuration, Class<?> type) {
//		super(configuration, type);
//	}
//
//	private SqlSource getSqlSourceFromAnnotations(Method method, Class<?> parameterType, LanguageDriver languageDriver) {
//		try {
//			Class<? extends Annotation> sqlAnnotationType = getSqlAnnotationType(method);
//			Class<? extends Annotation> sqlProviderAnnotationType = getSqlProviderAnnotationType(method);
//			if (sqlAnnotationType != null) {
//				if (sqlProviderAnnotationType != null) {
//					throw new BindingException("You cannot supply both a static SQL and SqlProvider to method named "
//							+ method.getName());
//				}
//				Annotation sqlAnnotation = method.getAnnotation(sqlAnnotationType);
//				final String[] strings = (String[]) sqlAnnotation.getClass().getMethod("value").invoke(sqlAnnotation);
//				return buildSqlSourceFromStrings(strings, parameterType, languageDriver);
//			} else if (sqlProviderAnnotationType != null) {
//				Annotation sqlProviderAnnotation = method.getAnnotation(sqlProviderAnnotationType);
//				return new ProviderSqlSource(assistant.getConfiguration(), sqlProviderAnnotation);
//			}
//			return null;
//		} catch (Exception e) {
//			throw new BuilderException("Could not find value method on SQL annotation.  Cause: " + e, e);
//		}
//	}
//
//	private Class<? extends Annotation> getSqlAnnotationType(Method method) {
//		return chooseAnnotationType(method, sqlAnnotationTypes);
//	}
//
//	private Class<? extends Annotation> getSqlProviderAnnotationType(Method method) {
//		return chooseAnnotationType(method, sqlProviderAnnotationTypes);
//	}
//
//	private Class<? extends Annotation> chooseAnnotationType(Method method, Set<Class<? extends Annotation>> types) {
//		for (Class<? extends Annotation> type : types) {
//			Annotation annotation = method.getAnnotation(type);
//			if (annotation != null) {
//				return type;
//			}
//		}
//		return null;
//	}
//
// }
