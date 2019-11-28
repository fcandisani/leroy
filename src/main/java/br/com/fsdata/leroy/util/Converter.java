package br.com.fsdata.leroy.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import br.com.fsdata.leroy.entities.Product;
import br.com.fsdata.leroy.exception.FileProcessException;
import br.com.fsdata.leroy.info.InfoData;



public class Converter {
		
	static boolean save = true;

	public static void readExcelSheet(String pathToFile) throws FileProcessException {
				
		try {
			
			
	        
	    	FileInputStream excelFile = new FileInputStream(new File(pathToFile));
	        XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
	
	        Sheet workSheet = workbook.getSheetAt(0);
	        
	        for (Row currentRow : workSheet) {
	        	
	        	save = true;
	            
	        	if (currentRow.getRowNum() > 2 ) {
	        		
	        		Product p = new Product();
	        	
		        	for (Cell currentCell : currentRow) {

		        		switch(currentCell.getColumnIndex()) {		            	
		            	
			            	case 0:
			            		p.setLm(new Double(currentCell.getNumericCellValue()).intValue());
			            		break;
			            	case 1:			            		
			            		p.setName(currentCell.getStringCellValue());
			            		break;
			            	case 2:			            		
			            		p.setFree_shipping(new Double(currentCell.getNumericCellValue()).intValue());
			            		break;
			            	case 3:
			            		p.setDescription(currentCell.getStringCellValue());
			            		break;
			            	case 4:
			            		p.setPrice(new Double(currentCell.getStringCellValue()));
			            		break;
			            	default:
		        		}
		            }
		        	
		        	if(InfoData.products.size() > 0 ) {		        		
		        		
		        		InfoData.products.forEach((pp) -> {

			        		if(pp.getLm().equals(p.getLm())) {		        			
			        			save = false;
			        		}	        		
			        	});			        	
		        	}
		        	
		        	if(save) {		        		
		        		InfoData.products.add(p);
		        	}
		        }	        	
	        }
	        
	        workbook.close();
	        
	    } catch (IOException  e) {
	    	
	        throw new FileProcessException(e);
	        
	    } catch (Exception e) {	    	
	    	
	    	throw new FileProcessException(e);
	    }		
	}
	
	
	
	public static Queue<Product> prepareToDelete(String pathToFile) throws FileProcessException {		
		return prepare(pathToFile);
	}
	
	public static Queue<Product> prepareToUpdate(String pathToFile) throws FileProcessException {		
		return prepare(pathToFile);
	}
	
	
	
	public static void delete(Queue<Product> products, Queue<Product> productsToBeDeleted) {
				
		productsToBeDeleted.forEach((p)->{			
			products.remove(p);			
		});		
	}
	
	public static void update(Queue<Product> products, Queue<Product> productsToBeDeleted) {
		
		productsToBeDeleted.forEach((p)-> {	
			
			products.removeIf(pp ->  pp.getLm().equals(p.getLm()));			
			products.add(p);
			
		});		
	}
	
	public static Queue<Product> prepare(String pathToFile) throws FileProcessException {
		
		Queue<Product> products = new LinkedList<>();
		
		try {
	        
	    	FileInputStream excelFile = new FileInputStream(new File(pathToFile));
	        XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
	
	        Sheet workSheet = workbook.getSheetAt(0);
	        
	        for (Row currentRow : workSheet) {
	            
	        	if (currentRow.getRowNum() > 2 ) {
	        		
	        		Product p = new Product();
	        	
		        	for (Cell currentCell : currentRow) {

		        		switch(currentCell.getColumnIndex()) {		            	
		            	
			            	case 0:
			            		p.setLm(new Double(currentCell.getNumericCellValue()).intValue());
			            		break;
			            	case 1:			            		
			            		p.setName(currentCell.getStringCellValue());
			            		break;
			            	case 2:			            		
			            		p.setFree_shipping(new Double(currentCell.getNumericCellValue()).intValue());
			            		break;
			            	case 3:
			            		p.setDescription(currentCell.getStringCellValue());
			            		break;
			            	case 4:
			            		p.setPrice(new Double(currentCell.getStringCellValue()));
			            		break;
			            	default:
		        		}
		            }
		        	
		        	products.add(p);
		        }	        	
	        }
	        
	        workbook.close();
	        
	    } catch (IOException  e) {
	    	e.printStackTrace();
	        throw new FileProcessException(e);
	        
	        
	    } catch (Exception e) {	    	
	    	e.printStackTrace();
	    	throw new FileProcessException(e);
	    }
		
		return products;
	}
}