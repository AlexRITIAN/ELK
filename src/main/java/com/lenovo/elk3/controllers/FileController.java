package com.lenovo.elk3.controllers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileController {
	
	@RequestMapping(value="/file/upload.do",method=RequestMethod.POST)  
    @ResponseBody  
    public void upload(@RequestParam("file")MultipartFile file,HttpServletRequest request,HttpServletResponse response) throws IOException{  
        String path = request.getSession().getServletContext().getRealPath("upload");  
        String fileName = file.getOriginalFilename();    
        File dir = new File(path,fileName);          
        if(!dir.exists()){  
            dir.mkdirs();  
        }  
        //MultipartFile自带的解析方法  
        file.transferTo(dir);  
        PrintWriter out = response.getWriter();
        out.print("http://10.99.205.240:8080/elk3/upload/" + fileName);
        out.close();
        
    }  
      
}
