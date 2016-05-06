package test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.text.NumberFormat;

public class tsetHan {

 public static final String DANWEI_1= "K";
 public static final String DANWEI_2="MM";
 public static final String DANWEI_3="B";
		// TODO 自动生成的方法存根
		public static void main(String path[]) throws Exception {
			/* URL u=new URL("http://route.showapi.com/582-4?showapi_appid=100&showapi_timestamp=20150918113111&type1_id=&type2_id=&keyword=&page=&showapi_sign=608E5FF1D6CFE030E68833014ABC3441");
		        InputStream in=u.openStream();
		        ByteArrayOutputStream out=new ByteArrayOutputStream();
		        try {
		            byte buf[]=new byte[1024];
		            int read = 0;
		            while ((read = in.read(buf)) > 0) {
		                out.write(buf, 0, read);
		            }
		        }  finally {
		            if (in != null) {
		                in.close();
		            }
		        }
		        byte b[]=out.toByteArray( );
		        System.out.println(new String(b,"utf-8"));
		}*/
			
			int x=1024002;
			int loop = checkNum( x, 1);
			
			java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00"+caseDanwei(loop)+"+");  
		/*	ddf1.setMaximumFractionDigits(2);*/
			String s= df.format((double)x/(Math.pow(1000,loop))) ;
			System.out.print(s);
	}
		

		public static int checkNum(int x,int loop){
			int y = x/1000;
			if(y>1000){
				loop+=1;
				return checkNum(y,loop);
			}
			return loop;
		}
		
		public static  String caseDanwei(int loop){
		  switch (loop) {
		case 1:
			return DANWEI_1;

		case 2:
			return DANWEI_2;
		case 3:
			return DANWEI_3;
		default:
			return "";
		}
		}
}
