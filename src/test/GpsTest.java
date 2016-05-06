package test;

import java.util.Scanner;

public class GpsTest {
	  public static void main(String[] args) {
	         
	        Scanner scanner = new Scanner(System.in);
	         
	        String currentline;
	         
	        String[] currentstringarr = new String[50];
	         
	        String res = "";
	         
	        String currenttime;
	         
	        while( true ){
	             
	            currentline = scanner.nextLine();
	             
	            if( currentline.equals("END") ){
	                 
	                break;
	             
	            }
	             
	            currentstringarr = currentline.split(",");
	             
	            if( jiaoyan(currentline)  ){                
	 
	                    currenttime =  currentstringarr[1].substring(0, 6);
	                     
	                    res = timeCover(currenttime);
	                 
	            }
	             
	 
	             
	        }
	         
	        echo(res);
	         
	    }
	     
	    private static boolean jiaoyan(String gps){
	         
	        String[] currentstringarr = new String[50];
	         
	        currentstringarr = gps.split(",");
	         
	        int i;
	         
	        char ch = ' ';
	         
	        boolean temp = currentstringarr[0].equals("$GPRMC") && currentstringarr[2].equals("A");
	         
	        int result;
	         
	        for(result=gps.charAt(1),i=2;ch!='*';i++,ch=gps.charAt(i)){
	            ch=gps.charAt(i);
	            result^=(int)ch;
	        }   
	         
	        result%=65536;
	         
	        String num = gps.substring( gps.length() - 2  );
	         
	        num = num.toLowerCase();
	         
	        return num.equals(Integer.toHexString(result)) && temp;
	    }   
	     
	    private static String timeCover(String in){
	         
	        int intin = Integer.parseInt(in);
	         
	        intin += 80000;
	         
	        if( intin >= 240000 ){
	            intin -= 240000;
	        }       
	         
	        return intin/10000 + ":" + intin/100%100 + ":" + intin%100 ;
	    }
	     
	    private static void echo(Object o){
	         
	        System.out.print(o);
	         
	    }   

}
