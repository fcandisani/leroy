package br.com.fsdata.leroy.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.regex.Pattern;

import br.com.fsdata.leroy.exception.FileSaveException;

public class FileUtil {

	
	public static void saveFile(String path,InputStream in) throws FileSaveException {

		try {
			
			OutputStream out = new FileOutputStream(new File(path));
			
			int read = 0;
			
			byte b [] = new byte[1024];
			
			while((read = in.read(b)) != -1) {				
				out.write(b, 0, read);
			}
			
			out.flush();
			out.close();
						
		}catch(IOException e) {
			
			throw new FileSaveException(e);
		}		
	}
	
	
	public static void fileRename(String  file) {
		
		File arquivo = new File(file);
        arquivo.renameTo(new File(file.replace(".xlsx",".old")));
	}
	
	public static boolean isXlsxFile(String FileName){
		
		boolean result = false;
		
		String [] sp = FileName.split(Pattern.quote("."));
		
		if(sp[sp.length -1].equalsIgnoreCase("xlsx")) {
			
			result = true;
		}
		return result;
	}
}
