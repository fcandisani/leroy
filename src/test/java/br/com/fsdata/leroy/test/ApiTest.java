package br.com.fsdata.leroy.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.io.File;
import java.io.FileInputStream;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.context.WebApplicationContext;
import br.com.fsdata.leroy.system.config.AppConfig;




@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={AppConfig.class})
@WebAppConfiguration
public class ApiTest {
	
	
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	@Before
	public void setup() throws Exception {
	    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	@Test
	public void testUpload() throws Exception{
		
		File f = new File("/Temp/mock/test/products_teste_webdev_leroy.xlsx");
		
		if(f.exists()) {
		
			MockMultipartFile ex = new MockMultipartFile("excel",
														 "products_teste_webdev_leroy.xlsx", 
														  MimeTypeUtils.MULTIPART_FORM_DATA_VALUE, 
														  new FileInputStream(f));
			
			
			 this.mockMvc.perform(MockMvcRequestBuilders.fileUpload("/upload")
				        .file(ex).accept(MimeTypeUtils.MULTIPART_FORM_DATA_VALUE))
			 			.andExpect(status().isOk())
			 			.andDo(print());
		}		 
	}

	
	@Test
	public void testStatus() throws Exception{
			
		 this.mockMvc.perform(get("/statusProcessFile"))
		 	.andExpect(status().isOk())
		 	.andDo(print());		 
	}
	
	
	@Test
	public void testUpdate() throws Exception{
			
		File f = new File("/Temp/mock/test/products_teste_webdev_leroy_update.xlsx");
		
		if(f.exists()) {
		
			MockMultipartFile ex = new MockMultipartFile("excel",
														 "products_teste_webdev_leroy_update.xlsx", 
														  MimeTypeUtils.MULTIPART_FORM_DATA_VALUE, 
														  new FileInputStream(f));
			
			
			 this.mockMvc.perform(MockMvcRequestBuilders.fileUpload("/updateProducts")
				        .file(ex).accept(MimeTypeUtils.MULTIPART_FORM_DATA_VALUE))
			 			.andExpect(status().isOk())
			 			.andDo(print());
			 
		}		 
	}
	
	@Test
	public void testDelete() throws Exception{
		
		File f = new File("/Temp/mock/test/products_teste_webdev_leroy_delete.xlsx");
		
		if(f.exists()) {
		
			MockMultipartFile ex = new MockMultipartFile("excel",
														 "products_teste_webdev_leroy_delete.xlsx", 
														  MimeTypeUtils.MULTIPART_FORM_DATA_VALUE, 
														  new FileInputStream(f));
			
			
			 this.mockMvc.perform(MockMvcRequestBuilders.fileUpload("/updateProducts")
				        .file(ex).accept(MimeTypeUtils.MULTIPART_FORM_DATA_VALUE))
			 			.andExpect(status().isOk())
			 			.andDo(print());
		}		 
	}
	
	
	@Test
	public void testGetAllUsers() throws Exception{
		
		 this.mockMvc.perform(get("/getAllProducts"))
		 	.andExpect(status().isOk())
		 	.andDo(print());		 
	}	
}
