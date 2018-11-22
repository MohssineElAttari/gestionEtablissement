

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExccelFile {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sh = wb.createSheet("firstsheet");
		HSSFRow row = sh.createRow(0);
		HSSFCell cell = row.createCell(0);
		cell.setCellValue("salah eddine");
		wb.write(new FileOutputStream("ARCHIVESBIR.xls"));
		
	}

}
