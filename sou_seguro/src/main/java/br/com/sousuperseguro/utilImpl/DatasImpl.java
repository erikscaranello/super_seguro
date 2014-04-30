package br.com.sousuperseguro.utilImpl;

import java.util.Calendar;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.springframework.stereotype.Component;

import br.com.sousuperseguro.util.Datas;

@Component
public class DatasImpl implements Datas {

	@Override
	public Calendar transformarNumeroEmData(HSSFCell numero) {
		
		Calendar cal = Calendar.getInstance();
		
		double cellNumeric;
        if(numero.getCellType() == 1) {
        	cellNumeric = Double.parseDouble(numero.getStringCellValue());
        } else {
        	cellNumeric = numero.getNumericCellValue();
        }
		
//		if (HSSFDateUtil.isCellDateFormatted(numero)) {
          cal.setTime(HSSFDateUtil.getJavaDate(cellNumeric));
//        } else {
//        	cal = null;
//        }
        return cal;
	}
	
}
