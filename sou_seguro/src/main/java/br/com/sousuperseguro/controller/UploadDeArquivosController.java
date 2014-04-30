package br.com.sousuperseguro.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.sousuperseguro.service.UploadDeArquivosService;

@Controller
public class UploadDeArquivosController {
	
	@Autowired
	private UploadDeArquivosService uploadDeArquivosService;
	
	@RequestMapping("/upload_de_arquivos")
	public ModelAndView uploadDeArquivos() {
		
		ModelAndView modelAndView = new ModelAndView("uploadDeArquivos/index");
		return modelAndView;
	}
	
	
	@RequestMapping("/upload_de_arquivos/upload")
	public ModelAndView upload(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			
			List<FileItem> itens = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
			uploadDeArquivosService.fazerUpload(itens);
			
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		
		return null;
		
//		ModelAndView modelAndView = new ModelAndView("uploadDeArquivos/index");
//		return modelAndView;
	}
}
