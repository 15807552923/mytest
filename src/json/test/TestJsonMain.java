package json.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.alibaba.fastjson.JSON;



public class TestJsonMain {

	
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		
		 String jsonString = null;
		 
		 
	
		 List<UserReAdd>  aList	 = JSON.parseArray(jsonString, UserReAdd.class);  
		  for(UserReAdd ua : aList){
			  System.out.println("ua=" + ua.toString());
		  }
		  UserReAdd urd = new UserReAdd();
		  urd.setReName("33333333");
		  urd.setReAdd("3333333");
		  urd.setRePhone("333333");
		  urd.setDefaultAp("1");
		  urd.setUuid(UUID.randomUUID().toString().replace("-", ""));
		  aList.add(urd);
		  String jsonText = JSON.toJSONString(aList, true);
		  System.out.println("jsonText="+jsonText);
	}

}
