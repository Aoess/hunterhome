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
	
	//对日期进行改造
	public static Timestamp dataTransform(Timestamp date,Long targetDate) {
		if(date == null) {
			date = new Timestamp(targetDate);
		}
		return date;
	}

}
