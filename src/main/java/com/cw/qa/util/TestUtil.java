package com.cw.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.prefs.InvalidPreferencesFormatException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.JSONArray;
import org.json.JSONObject;

import com.cw.qa.base.BaseClass;



public class TestUtil extends BaseClass{

	public static long Page_Load_Time = 20;
	public static long IMPLICIT_WAIT = 10;
	public static int Status_Code_200 = 200;
	public static String TESTDATA_SHEET_PATH = properties.getProperty("testdatasheet");
	static HSSFRow row;static HSSFCell cell;
	//public String testdata;
	
	public static String getValueByJPath(JSONObject responsejson, String jpath)
	{
		Object obj = responsejson;
		for(String s:jpath.split("/"))
		{
			if(!s.isEmpty())
			{
				if(!s.contains("[")||s.contains("]"))
				{
					obj = ((JSONObject)obj).get(s);
				}
				else if(s.contains("[")||s.contains("]"))
				{
					obj=((JSONArray)((JSONObject)obj).get(s.split("\\[")[0])).get(Integer.parseInt(s.split("\\[")[1].replace("]", "")));
				}
			}
			
			
		}
		return obj.toString();
	}
	
	public static HashMap<String,String> addvalues(String data){
		HashMap<String,String> newmap =  new HashMap<String,String>();
			String complete_data = data;
			//System.out.println(data);
			String[] eachrecord = complete_data.split(";");
			for(String str : eachrecord)
			{
				System.out.println(str);
				String[] temp = str.split(",");
				newmap.put(temp[0], temp[1]);
			}
		
		return newmap;
	}
	
	public static List<String> ReadData(String sheetName) throws IOException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		//File file = new File(fileName);
		FileInputStream stream = new FileInputStream(TESTDATA_SHEET_PATH);
		HSSFWorkbook workbook = new HSSFWorkbook(stream);
		HSSFSheet sheet1 = workbook.getSheet(sheetName);
		List<String> testdata = new ArrayList<String>();
		for(int i=0; i<sheet1.getPhysicalNumberOfRows();i++)
		{
			row = sheet1.getRow(i);
				System.out.println("Row no: "+ i);
				//testdata.clear();
			for(int j=0; j<row.getPhysicalNumberOfCells();j++)
			{
				cell=row.getCell(j);
				//System.out.println(cell.getStringCellValue());
				testdata.add(cell.getStringCellValue());
			}
			System.out.println("items: "+testdata+" added in list");
						
		FileOutputStream output = new FileOutputStream(TESTDATA_SHEET_PATH); // It will unlock the file which was locked by FileInputStream
		workbook.write(output);
	}
		return testdata;
}
}
