package br.com.fsdata.leroy.controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Queue;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.fsdata.leroy.entities.Product;
import br.com.fsdata.leroy.exception.FileProcessException;
import br.com.fsdata.leroy.exception.FileSaveException;
import br.com.fsdata.leroy.info.InfoData;
import br.com.fsdata.leroy.util.Converter;
import br.com.fsdata.leroy.util.FileUtil;

@RestController
public class DataController {
	
	@RequestMapping(
			value = "/upload",
			method = RequestMethod.POST,
			consumes = MimeTypeUtils.MULTIPART_FORM_DATA_VALUE
			)
	public void uploadData(@RequestParam(value = "excel") MultipartFile excel, HttpServletRequest request)  {

		
		String path = InfoData.pathToSave + excel.getOriginalFilename();
		
		try {
			
			if(FileUtil.isXlsxFile(excel.getOriginalFilename())) {
			
				FileUtil.saveFile(path,excel.getInputStream());
				
				Converter.readExcelSheet(path);
				
				InfoData.statusFile = "Ultimo arquivo " + excel.getOriginalFilename() + " processado com sucesso - " + new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss").format(new Date());;			
			}
		
		}catch (FileProcessException | FileSaveException | IOException e) {
			
			InfoData.statusFile = "Ultimo arquivo " + excel.getOriginalFilename() + " processado com erro: " + e.getMessage();			
		}
	}
	
	@RequestMapping(
			value = "/statusProcessFile",
			method = RequestMethod.GET,
			produces = MimeTypeUtils.APPLICATION_JSON_VALUE
			)
	public String uploadData()  {
		
		if (InfoData.statusFile != null) {
			
			return InfoData.statusFile;
		} 
		
		return "Ainda nao foram processados arquivos";
	}
	
	@RequestMapping(
			value = "/getAllProducts",
			method = RequestMethod.GET,
			produces = MimeTypeUtils.APPLICATION_JSON_VALUE
			)
	public Queue<Product> getAllProducts()  {
		return InfoData.products;
	}
	
	
	@RequestMapping(
			value = "/updateProducts",
			method = RequestMethod.POST,
			consumes = MimeTypeUtils.MULTIPART_FORM_DATA_VALUE
			)
	public void updateProduct(@RequestParam(value = "excel") MultipartFile excel, HttpServletRequest request)  {
		
		String path = InfoData.pathToSave + excel.getOriginalFilename();
		
		try {
			
			if(InfoData.products != null && InfoData.products.size() > 0 ) {
			
				if(FileUtil.isXlsxFile(excel.getOriginalFilename())) {
				
					FileUtil.saveFile(path,excel.getInputStream());

					Converter.prepareToUpdate(path);
					
					Converter.update(InfoData.products, Converter.prepareToDelete(path));

					InfoData.statusFile = "Ultimo arquivo " + excel.getOriginalFilename() + " processado com sucesso - " + new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss").format(new Date());			
				}
			
			}
		
		}catch (FileProcessException | FileSaveException | IOException e) {
			
			InfoData.statusFile = "Ultimo arquivo " + excel.getOriginalFilename() + " processado com erro: " + e.getMessage();			
		}
	}
	
	@RequestMapping(
			value = "/deleteProducts",
			method = RequestMethod.POST,
			consumes = MimeTypeUtils.MULTIPART_FORM_DATA_VALUE
			)
	public void deleteProduct(@RequestParam(value = "excel") MultipartFile excel, HttpServletRequest request)  {

		
		String path = InfoData.pathToSave + excel.getOriginalFilename();
		
		try {
			
			if(InfoData.products != null && InfoData.products.size() > 0 ) {
				if(FileUtil.isXlsxFile(excel.getOriginalFilename())) {
				
					FileUtil.saveFile(path,excel.getInputStream());
					
					Converter.prepareToDelete(path);
					
					Converter.delete(InfoData.products, Converter.prepareToDelete(path));
					
					InfoData.statusFile = "Ultimo arquivo " + excel.getOriginalFilename() + " processado com sucesso - " + new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss").format(new Date());			
				}
			}
		
		}catch (FileProcessException | FileSaveException | IOException e) {
			
			InfoData.statusFile = "Ultimo arquivo " + excel.getOriginalFilename() + " processado com erro: " + e.getMessage();			
		}
	}
}
