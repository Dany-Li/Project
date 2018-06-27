package com.atguigu.ssh.converters;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;



/**
 * 时间是一个字符串, 需要转为一个 Date 类型的对象
 *
 */
public class SSHDateConverter extends StrutsTypeConverter {
	
	private DateFormat dateFormat;
	
	{
		dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	}

	@Override
	public Object convertFromString(Map arg0, String[] arg1, Class arg2) {
		// TODO Auto-generated method stub
		//这里用 == 而不是用 .equals 是因为 class 只有一个，而对象可以有很多个
		if(arg2 == Date.class) {
			try {
				return dateFormat.parse(arg1[0]);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public String convertToString(Map arg0, Object arg1) {
		// TODO Auto-generated method stub
		if(arg1 instanceof Date) {
			return dateFormat.format((Date)arg1);
		}
		return null;
	}

}
