package br.com.sousuperseguro.util;

import java.util.Calendar;

import org.apache.poi.hssf.usermodel.HSSFCell;

public interface Datas {
	Calendar transformarNumeroEmData(HSSFCell numero);
}
