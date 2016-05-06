package test;
import java.io.BufferedInputStream;  
import java.io.DataOutputStream;  
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;  
import java.io.InputStreamReader;  
import java.io.Reader;  
import java.net.HttpURLConnection;  
import java.net.URL;  
import java.net.URLEncoder;  
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
  
public class ExpertIntroduceCut {  
   public static ExpUserInfoExcel cc(String url,ExpUserInfoExcel cu) {  
       Document doc; 
     	String introduce="" ;
     	 String gender ="";
     	 String locationCity="";
       try { 
           doc = Jsoup.connect(url).get(); 
           Element link = doc.getElementsByClass("mod_big").eq(1).first().child(1);
           introduce =  link.text();
           cu.setDescription(introduce);
           
           if(doc.getElementById("desc_temp_left").child(1).text().contains("认证信息")){
        	   String[] genderStr = doc.getElementById("desc_temp_left").child(2).text().split("：");
               gender= genderStr[genderStr.length-1];
               locationCity= genderStr[1].split(" ")[0];
           }else if (doc.getElementById("desc_temp_left").child(1).text().contains("性别")){
        	     String[] genderStr = doc.getElementById("desc_temp_left").child(1).text().split("：");
                 gender= genderStr[genderStr.length-1];
                 locationCity= genderStr[1].split(" ")[0];
           }else{
        	   String[] genderStr = doc.getElementById("desc_temp_left").child(0).text().split("：");
               gender= genderStr[genderStr.length-1];
               locationCity= genderStr[1].split(" ")[0];
           }
      
           cu.setGender(gender);
           cu.setLocationCity(locationCity);
           System.out.println("gender="+gender);
           
          /* 	 Document childhtml = Jsoup.parse(elehtml); 
           	 Element til_link = childhtml.getElementsByClass("firm_txt_center").first();
           	 String goodAtField =til_link.child(0).text();
           	 String goodAtIndustry =til_link.child(1).text();
           	 String locationCity =til_link.child(3).text();
           	 String description =til_link.child(5).text();*/
           	 
           	 /*String termPrice =til_link.child(0).text();
           	 
           	 String goodAtField = til_links.first().child(1).text();
           	 String locationCity = til_links.first().nextElementSibling().child(0).text();
           	 String goodAtIndustry = til_links.first().nextElementSibling().child(1).text();
           	 */
      	     System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
           	 System.out.println("introduce : " + introduce); 
           	 System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
           	 
           
       } catch (IOException e) { 
           e.printStackTrace(); 
       } 
        
		return cu;  
    }  
  
    public static void main(String args[]) throws Exception { 
    	System.out.println(System.getProperty("java.io.tmpdir"));


    }  
    }

