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
  
public class webCut {  
   /* public static String cc(String leibie, String num) {  
        StringBuffer temp = new StringBuffer();  
        try {  
            System.out.println(leibie);  
            System.out.println(num);  
            String url = "http://www.baidu.com/jiaojing/ser.php";  
            HttpURLConnection uc = (HttpURLConnection)new URL(url).  
                                   openConnection();  
            uc.setConnectTimeout(10000);  
            uc.setDoOutput(true);  
            uc.setRequestMethod("GET");  
            uc.setUseCaches(false);  
            DataOutputStream out = new DataOutputStream(uc.getOutputStream());  
  
            // 要传的参数  
            String s = URLEncoder.encode("ra", "GB2312") + "=" +  
                       URLEncoder.encode(leibie, "GB2312");  
            s += "&" + URLEncoder.encode("keyword", "GB2312") + "=" +  
                    URLEncoder.encode(num, "GB2312");  
            // DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写道流里面  
            out.writeBytes(s);  
            out.flush();  
            out.close();  
            InputStream in = new BufferedInputStream(uc.getInputStream());  
            Reader rd = new InputStreamReader(in, "Gb2312");  
            int c = 0;  
            while ((c = rd.read()) != -1) {  
                temp.append((char) c);  
            }  
            System.out.println(temp.toString());  
            in.close();  
  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return temp.toString();  
    }  */
  
    public static void main(String args[]) throws Exception { 
    	String  title = "";
        // Parse HTML String using JSoup library 
     
    	 List<ConsUserInfoExcel>  list = new ArrayList();  
        // JSoup Example 2 - Reading HTML page from URL 
        Document doc; 
        String urlF = "http://www.taoke.com/company/0/0/0/0/0/0/0/def/";
        String urlE = ".htm";
        //循环次数页数
        for(int i=900 ; i<1000;i++){
        	String endUrl=urlF+i+urlE;
        	 try { 
                 doc = Jsoup.connect(endUrl).get(); 
                 title = doc.title(); 
                 Elements links = doc.getElementsByClass("firm");
                 for(Element element : links){
                 	/* System.out.println("html : " + element.html()); */
                 	
                 	ConsUserInfoExcel exp = new ConsUserInfoExcel();
                 
                 	String elehtml =  element.html();
                 	/*System.out.println("elehtml:"+ element.html());*/
                 	 Document childhtml = Jsoup.parse(elehtml); 
                 	 
                 	 String name= childhtml.select("img").first().attr("alt");
                 	 String smallImgSrc= childhtml.select("img").first().attr("src");
                 	 String aHref= childhtml.select("a").first().attr("href");
                 	 String toUrl = "http://www.taoke.com/"+aHref;
                 	 exp =  IntroduceCut.cc(toUrl,exp);
                 	 String url = "http://www.taoke.com/"+smallImgSrc;
                 	 ImgBase64.save(url); 
                      String fileName = url.substring(url.lastIndexOf("/"));  
                      String filePath = ImgBase64.PIC_DIR + "/" + fileName;  
                      String baseImg = ImgBase64.imageToBase64(filePath);
                      StringBuffer sb = new StringBuffer();
                      sb.append("data:image/png;base64,");
                      sb.append(baseImg);
                      String smallImg = sb.toString();
                 	 Element til_link = childhtml.getElementsByClass("firm_txt_center").first();
                 	 String goodAtField =til_link.child(0).text();
                 	 String goodAtIndustry =til_link.child(1).text();
                 	 String locationCity =til_link.child(3).text();
                 	 
                 	 
                 	 /*String termPrice =til_link.child(0).text();
                 	 
                 	 String goodAtField = til_links.first().child(1).text();
                 	 String locationCity = til_links.first().nextElementSibling().child(0).text();
                 	 String goodAtIndustry = til_links.first().nextElementSibling().child(1).text();
                 	 */
                 	 exp.setGoodAtField(goodAtField);
                 	 exp.setLocationCity(locationCity);
                 	 exp.setGoodAtIndustry(goodAtIndustry);
                 	 exp.setInfoTitle(name);
                 	 exp.setSmallImage(smallImg);
                 	 //System.out.println("smallImg : " + smallImg); 
                 	/* System.out.println("termPrice : " + termPrice); */
                 	 System.out.println("name : " + name); 
                 	 System.out.println("goodAtField : " + goodAtField); 
                 	 System.out.println("locationCity : " + locationCity); 
                 	 System.out.println("goodAtIndustry : " + goodAtIndustry); 
                 	 System.out.println("====================================================");
                 	 list.add(exp);
                 	 
                 }
             } catch (IOException e) { 
                 e.printStackTrace(); 
             } 
        
        
        }
        
       
        
        
        HSSFWorkbook wb = new HSSFWorkbook();  
        HSSFSheet sheet = wb.createSheet("机构表");  
        HSSFRow row = sheet.createRow((int) 0);  
        HSSFCellStyle style = wb.createCellStyle();  
        
        
        HSSFCellStyle cellStyle2 = wb.createCellStyle();  
        HSSFDataFormat format = wb.createDataFormat();  
        cellStyle2.setDataFormat(format.getFormat("@"));  
        
        
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
  
        HSSFCell cell = row.createCell((short) 0);  
        cell.setCellValue("用户名");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 1);  
        cell.setCellValue("密码");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 2);  
        cell.setCellValue("公司名称");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 3);  
        cell.setCellValue("注册时间");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 4);  
        cell.setCellValue("公司地址");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 5);  
        cell.setCellValue("擅长领域");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 6);  
        cell.setCellValue("擅长行业");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 7);  
        cell.setCellValue("公司网址");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 8);  
        cell.setCellValue("公司简介");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 9);  
        cell.setCellValue("联系电话");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 10);  
        cell.setCellValue("头像");  
        cell.setCellStyle(style);  
        
  
        for (int i = 0; i < list.size(); i++)  
        {  
            row = sheet.createRow((int) i + 1);  
            ConsUserInfoExcel stu = (ConsUserInfoExcel) list.get(i);  
            row.createCell((short) 0).setCellValue((String) stu.getUsername());  
            row.createCell((short) 1).setCellValue(stu.getPassword());  
            row.createCell((short) 2).setCellValue((String) stu.getInfoTitle());  
            row.createCell((short) 3).setCellValue((String) stu.getBirthday());  
            row.createCell((short) 4).setCellValue((String) stu.getLocationCity());  
            row.createCell((short) 5).setCellValue((String) stu.getGoodAtField());  
            row.createCell((short) 6).setCellValue((String) stu.getGoodAtIndustry());  
            row.createCell((short) 7).setCellValue("");  
            row.createCell((short) 8).setCellValue((String) stu.getDescription());  
            row.createCell((short) 9).setCellValue("");  
            if(stu.getSmallImage().getBytes().length<32000){
            	 row.createCell((short) 10).setCellValue(stu.getSmallImage());  
            }
           
        }  
        try  
        {  
            FileOutputStream fout = new FileOutputStream("D:\\students.xls");  
            wb.write(fout);  
            System.out.println("ok");
            fout.close();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
        }  
    }  
    }

