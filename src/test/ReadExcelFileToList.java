package test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ReadExcelFileToList {
	 public static List<UserInfoExcel> readExcelData(String fileName) {  
	        List<UserInfoExcel> countriesList = new ArrayList<UserInfoExcel>();  
	           
	        try {  
	            //Create the input stream from the xlsx/xls file  
	            FileInputStream fis = new FileInputStream(fileName);  
	               
	            //Create Workbook instance for xlsx/xls file input stream  
	            Workbook workbook = null;  
	            if(fileName.toLowerCase().endsWith("xlsx")){  
	                workbook = new XSSFWorkbook(fis);  
	            }else if(fileName.toLowerCase().endsWith("xls")){  
	                workbook = new HSSFWorkbook(fis);  
	            }  
	               
	            //Get the number of sheets in the xlsx file  
	            int numberOfSheets = workbook.getNumberOfSheets();  
	               
	            Sheet  firstSheet = workbook.getSheetAt(0);
	            Row  fisrtRow = firstSheet.getRow(0);
	            // 标题总列数
	            int colNum = fisrtRow.getPhysicalNumberOfCells();
	            
	            //loop through each of the sheets  
	            for(int i=0; i < numberOfSheets; i++){  
	                //Get the nth sheet from the workbook  
	                Sheet sheet = workbook.getSheetAt(i);  
	                //every sheet has rows, iterate over them  
	               
	                Iterator<Row> rowIterator = sheet.iterator();  
	                while (rowIterator.hasNext()){  
	                		  String username = "";  
	  	                    String password = "";  
	  	                    String infoTitle ="";
	  	                       
	  	                    //Get the row object  
	  	                    Row row = rowIterator.next();  
	  	                       
	  	                    //Every row has columns, get the column iterator and iterate over them  
	  	                    Iterator<Cell> cellIterator = row.cellIterator();  
	  	                        
	  	                    while (cellIterator.hasNext()){  
	  	                        //Get the Cell object  
	  	                        Cell cell = cellIterator.next();  
	  	                           
	  	                        //check the cell type and process accordingly  
	  	                        switch(cell.getCellType()){  
	  	                        case Cell.CELL_TYPE_STRING:  
	  	                            if(username.equalsIgnoreCase("")){  
	  	                            	username = cell.getStringCellValue().trim();  
	  	                            }else if(password.equalsIgnoreCase("")){  
	  	                            	password = cell.getStringCellValue().trim();  
	  	                            }else if(infoTitle.equalsIgnoreCase("")){  
	  	                            	infoTitle= cell.getStringCellValue().trim(); 
	  	                            }else{
	  	                            }
	  	                            break;  
	  	                        case Cell.CELL_TYPE_NUMERIC:  
	  	                            System.out.println("Random data::"+cell.getNumericCellValue());  
	  	                        }  
	  	                    } //end of cell iterator  
	  	                    UserInfoExcel c = new UserInfoExcel(username, password,infoTitle);  
	  	                    countriesList.add(c);  
	                	
	                  
	                } //end of rows iterator  
	                   
	                   
	            } //end of sheets for loop  
	               
	            //close file input stream  
	            fis.close();  
	               
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
	           
	        return countriesList;  
	    }  
	   
	    public static void main(String args[]){  
	        List<UserInfoExcel> list = readExcelData("D:\\com.xlsx");  
	        System.out.println("Country List\n"+list);  
	    }  

}
