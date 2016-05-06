package com.show.api.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class traceno {

	public static void main(String[] args) {
		
		 Pattern pattern = Pattern.compile("^(-)?(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){1,2})?$");
		  Matcher matcher = pattern.matcher("3333333333.9");
		  System.out.println(matcher.matches());
	}

	
	 public static String getMaxId(){  
         SimpleDateFormat format= new SimpleDateFormat("yyyyMMddHHmmss");  
         String date=format.format(new Date());  
         String firstNo="MN";  
         
         Random rd = new Random();
         String n="";
         int getNum;
         do {
          getNum = Math.abs(rd.nextInt())%10 + 48;//产生数字0-9的随机数
          //getNum = Math.abs(rd.nextInt())%26 + 97;//产生字母a-z的随机数
          char num1 = (char)getNum;
          String dn = Character.toString(num1);
          n += dn;
         } while (n.length()<6);
         
         String lastNo=firstNo+date+n;  
     return lastNo;  
       
 }  
	 
	 
	 public static boolean isNumber(String str) 
	    { 
	        java.util.regex.Pattern pattern=java.util.regex.Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$"); // 判断小数点后一位的数字的正则表达式
	        java.util.regex.Matcher match=pattern.matcher(str); 
	        if(match.matches()==false) 
	        { 
	           return false; 
	        } 
	        else 
	        { 
	           return true; 
	        } 
	    }
}
