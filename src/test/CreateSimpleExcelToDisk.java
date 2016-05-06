package test;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class CreateSimpleExcelToDisk {
	 /** 
     * @功能：手工构建一个简单格式的Excel 
     */  
    private static List<ExpUserInfoExcel> getStudent() throws Exception  
    {  
        List list = new ArrayList();  
        SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");  
  
        ExpUserInfoExcel user1 = new ExpUserInfoExcel("1","2","2", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1");  
        ExpUserInfoExcel user2 = new ExpUserInfoExcel("2", "2", "2", null, null, "2", null, "2", null, "2", null, null, "2");  
        ExpUserInfoExcel user3 = new ExpUserInfoExcel("3", null, "3", null, null, "3", null, null, "3", null, "3", null, "3");  
        list.add(user1);  
        list.add(user2);  
        list.add(user3);  
  
        return list;  
    }  
  
    public static void main(String[] args) throws Exception  
    {  
        // 第一步，创建一个webbook，对应一个Excel文件  
        HSSFWorkbook wb = new HSSFWorkbook();  
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet("专家表");  
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
        HSSFRow row = sheet.createRow((int) 0);  
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style = wb.createCellStyle();  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
  
        HSSFCell cell = row.createCell((short) 0);  
        cell.setCellValue("用户名");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 1);  
        cell.setCellValue("密码");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 2);  
        cell.setCellValue("专家名称");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 3);  
        cell.setCellValue("性别");  
        cell.setCellStyle(style);  
  
        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，  
        List list = CreateSimpleExcelToDisk.getStudent();  
  
        for (int i = 0; i < list.size(); i++)  
        {  
            row = sheet.createRow((int) i + 1);  
            ExpUserInfoExcel stu = (ExpUserInfoExcel) list.get(i);  
            // 第四步，创建单元格，并设置值  
            row.createCell((short) 0).setCellValue((String) stu.getUsername());  
            row.createCell((short) 1).setCellValue(stu.getPassword());  
            row.createCell((short) 2).setCellValue((String) stu.getInfoTitle());  
            cell = row.createCell((short) 3);  
            cell.setCellValue(stu.getGender());  
        }  
        // 第六步，将文件存到指定位置  
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
