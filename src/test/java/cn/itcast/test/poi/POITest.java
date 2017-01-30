package cn.itcast.test.poi;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.junit.Test;


/**
 * 解析 Excel 和 生成 Excel
 */
public class POITest {
	@Test
	// 解析 info.xls 文件
	public void demo1() throws Exception{
		// 1. 获得 HSSFWorkbook (针对 xls 格式文件)
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(new FileInputStream("info.xls"));
		
		
		// 2. 获取要解析 sheet
		HSSFSheet sheet = hssfWorkbook.getSheet("Sheet1");// 通过名称获得 Sheet
		HSSFSheet sheet2 = hssfWorkbook.getSheetAt(0);// 获得第一个Sheet,通过下标获得
		// 3. 解析每一行
		for(Row row: sheet){
			// 3. 解析每一格
			for(Cell cell: row){
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					System.out.print(cell.getStringCellValue() + " ");
					break;
				case Cell.CELL_TYPE_NUMERIC:
					System.out.print(cell.getNumericCellValue() + " ");
					break;

				}
			}
			System.out.println();
			System.out.println(row.getCell(0).getStringCellValue());
		}
		
	}
	@Test
	// 在桌面上生成一个Excel 文件
	public void demo2() throws FileNotFoundException, IOException{
		//1. 创建一个空的工作薄
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
		//2. 在工作薄建立 Sheet
		HSSFSheet sheet = hssfWorkbook.createSheet("数据信息");
		
		//3. 向sheet写入行数据
		HSSFRow row = sheet.createRow(0);
	
		//4. 向row中单元格进行数据输出
		row.createCell(0).setCellValue("产品");
		row.createCell(1).setCellValue("价格");
		
		//5. 将Excell数据输出到硬盘上
		hssfWorkbook.write(new FileOutputStream("/Users/poirot/Desktop/proinfo.xls"));
		
	}
	
}
