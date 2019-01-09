package com.windhunter.hunterhome.validation;

import java.sql.Timestamp;

public class ManualValidation {
	
	//对UUID生成的id号进行校验和改造以便可以在查询中使用
	public static String UUIDtransform(String id, String defaultStr) {
		if(id != null) {
			id = id.trim();
			if(id.length() > 34 || id.length() < 32 || id.equals("undefind")) {
				id = defaultStr;
			}
		}
		return id;
	}

	//动态校验字符串长度
	public static String strTransform(String id, String defaultStr, int minLength, int maxLength) {
		if(id != null) {
			id = id.trim();
			if(id.length() > maxLength || id.length() < minLength || id.equals("undefind")) {
				id = defaultStr;
			}
		}
		return id;
	}
	
	//对日期进行改造
	public static Timestamp dataTransform(Timestamp date,Timestamp targetDate) {
		if(date == null) {
			date = targetDate;
		}
		return date;
	}

	//对post权限码进行改造
	public static int processTransform(int process,int defaultInt) {
		if(process < 0 || process > 9) {
			process = defaultInt;
		}
		return process;
	}

	//对type权限码进行改造
	public static int typeIdTransform(int type_id,int defaultInt) {
		if(type_id < 0 || type_id > 9) {
			type_id = defaultInt;
		}
		return type_id;
	}

	//对搜索关键字进行改造
	public static String sreachTransform(String sreachStr, String defaultStr) {
		if(sreachStr == null || !sreachStr.matches("[A-Za-z0-9]*")) {
			sreachStr = defaultStr;
		}
		return sreachStr;
	}
}
