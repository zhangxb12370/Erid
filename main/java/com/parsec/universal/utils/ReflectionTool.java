package com.parsec.universal.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by husu on 14-8-18.
 */
public class ReflectionTool {

	/**
	 * 通过配置的字段数组，将一个POJO的List，变成一个装有Map的List，目的在于生成JSONArray时，能精简数据 for example
	 * ：
	 *
	 * List<SysUser> list = sysUserDao.getSysUserList(); String[] fields = new
	 * String[]{"id","userName","trueName","roleName","createDateFormat"};
	 * List<Map<String,Object>> mapList = ReflectionTool.pojoToMap(list,
	 * fields); JSONArray jsonArray = new JSONArray(mapList);
	 *
	 * @param fromList
	 * @param fields
	 * @return
	 */

	@SuppressWarnings("rawtypes")
	public static List<Map<String, Object>> pojoToMap(List fromList,String... fields) {

		List<Map<String, Object>> mapList = new LinkedList<Map<String, Object>>();

		if (fromList == null) {
			return mapList;
		}
		for (Object obj : fromList) {

			Map<String, Object> map = new HashMap<String, Object>();

			for (String field : fields) {
				String methodName = "get" + field.substring(0, 1).toUpperCase()
						+ field.substring(1);
				try {
					Method getMethod = obj.getClass().getMethod(methodName,
							new Class[] {});
					Object valueObj = null;
					try {
						valueObj = getMethod.invoke(obj, new Object[] {});
					} catch (IllegalAccessException e) {
					} catch (InvocationTargetException e) {
					}
					if (valueObj == null) {
						valueObj = "";
					}
					map.put(field, valueObj);
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				}

			}
			mapList.add(map);
		}

		return mapList;
	}

	/**
	 * 通过配置例外的字段数组，将一个POJO的List，变成一个装有Map的List，目的在于生成JSONArray时，能精简数据 for
	 * example ：
	 *
	 * List<SysUser> list = sysUserDao.getSysUserList();
	 *
	 * List<Map<String,Object>> mapList =
	 * ReflectionTool.pojoToMapExceptFields(list, “id”); //按除了id的全部字段生成Map
	 * JSONArray jsonArray = new JSONArray(mapList);
	 *
	 * @param fromList
	 * @param fields
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static List<Map<String, Object>> pojoToMapExceptFields(List fromList, String... fields) {
		List<Map<String, Object>> mapList = new LinkedList<Map<String, Object>>();

		if (fromList == null) {
			return mapList;
		}
		for (Object obj : fromList) {

			Map<String, Object> map = new HashMap<String, Object>();

			Field[] declareFields = obj.getClass().getDeclaredFields();

			for (Field field : declareFields) {

				String fieldName = field.getName();

				boolean excepted = false;

				for (String exceptField : fields) {
					if (fieldName.equals(exceptField)) {
						excepted = true;
						break;
					}
				}
				if (excepted) {
					continue;
				}
				String methodName = "get"
						+ fieldName.substring(0, 1).toUpperCase()
						+ fieldName.substring(1);
				try {
					Method getMethod = obj.getClass().getMethod(methodName,
							new Class[] {});
					Object valueObj = null;
					try {
						valueObj = getMethod.invoke(obj, new Object[] {});

					} catch (IllegalAccessException e) {

					} catch (InvocationTargetException e) {

					}
					if (valueObj == null) {
						valueObj = "";
					}
					map.put(fieldName, valueObj);
				} catch (NoSuchMethodException e) {

				}
			}
			mapList.add(map);
		}
		return mapList;
	}

}
