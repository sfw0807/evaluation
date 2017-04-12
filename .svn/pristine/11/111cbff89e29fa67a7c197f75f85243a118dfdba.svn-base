package com.fykj.product.evaluation.api.filling.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fykj.platform.kernel._c.ServerSessionHolder;
import com.fykj.platform.kernel._c.model.InvokeResult;
import com.fykj.platform.kernel._c.model.SessionUser;
import com.fykj.platform.kernel.parameter.annotation.ParamValidation4Controller;
import com.fykj.product.evaluation.api.filling.model.RptResource;
import com.fykj.product.evaluation.api.filling.service.RptAnswerServiceApi;
import com.fykj.product.evaluation.api.filling.service.RtpResourceServiceApi;
import com.fykj.product.evaluation.api.filling.vo.RptResourceInVO;

@Controller
@RequestMapping("/resource")
@ParamValidation4Controller
public class ResourceController {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Value("${resouce.path}")
	private String resourcePath;
	
	@Autowired
	RtpResourceServiceApi rtpResourceService;
	
	@Autowired
	RptAnswerServiceApi rptAnswerService;
	
	@RequestMapping("/upload")
	@ResponseBody
	public InvokeResult upload(RptResourceInVO rptResourceInVO, @RequestParam MultipartFile[] uploadFiles,HttpServletRequest request)
			throws IOException {
		
		SessionUser sessionUser= ServerSessionHolder.getSessionUser();
		
		// 如果只是上传一个文件，则只需要MultipartFile类型接收文件即可，而且无需显式指定@RequestParam注解
		
		   // 如果想上传多个文件，那么这里就要用MultipartFile[]类型来接收文件，并且还要指定@RequestParam注解
		
		   // 并且上传多个文件时，前台表单中的所有<input
		
		   // type="file"/>的name都应该是myfiles，否则参数里的myfiles无法获取到所有上传的文件
		List<RptResource> uploadFileList = new ArrayList<RptResource>();
		
		for (MultipartFile myfile : uploadFiles) {
		
		    if (myfile.isEmpty()) {
		
		    	LOGGER.info("文件未上传");
		
		 } else {
			
			 LOGGER.info("文件长度: " + myfile.getSize());
			
			 LOGGER.info("文件类型: " + myfile.getContentType());
			
			 LOGGER.info("文件名称: " + myfile.getName());
			
			 LOGGER.info("文件原名: " + myfile.getOriginalFilename());
			
			 LOGGER.info("========================================");
			
			 // 如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\WEB-INF\\upload\\文件夹中
			
			 // 这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的
			
			 // String realPath =
			
			 // request.getSession().getServletContext().getRealPath("/FileTest/WebContent/WEB-INF/upload");
			
			 /*String filePath = request.getSession().getServletContext()
			
			         .getRealPath("/WEB-INF/upload") + File.separator + sessionUser.getId() + File.separator + rptResourceInVO.getProjectId();
			*/
			 
			 String filePath = buildFilePath(rptResourceInVO, sessionUser);
			
			 LOGGER.info("filePath is " + filePath);
			 String newName = buildFile(myfile.getOriginalFilename());
			 FileUtils.copyInputStreamToFile(myfile.getInputStream(), new File(filePath, newName));
			 
			 RptResource res = new RptResource();
			 res.setOrgName(myfile.getOriginalFilename());
			 res.setFilePath(filePath + "/" + newName);
//			 res.setFilePath(filePath + File.separator + newName);
			 res.setFileName(newName);
			 res.setProjectId(rptResourceInVO.getProjectId());
			 res.setTargetId(rptResourceInVO.getTargetId());
			 uploadFileList.add(res);
		
		 }
		
	}
		rptResourceInVO.setUploadFileList(uploadFileList);
		rptAnswerService.saveOrUpdateRptResource(rptResourceInVO);
		return InvokeResult.success(rptResourceInVO);
	}

	private String buildFilePath(RptResourceInVO rptResourceInVO, SessionUser sessionUser) {
//		String filePath = resourcePath + File.separator + sessionUser.getId() + File.separator + rptResourceInVO.getProjectId();
		String filePath = resourcePath + "/" + sessionUser.getId() + "/" + rptResourceInVO.getProjectId();
		return filePath;
	}
	
	private String buildFile(String oldFileName) {
		String newFileName = "";
		if(oldFileName.contains(".")) {
			String suffix = oldFileName.substring(oldFileName.lastIndexOf(".")+1);
			newFileName = oldFileName.substring(0, oldFileName.lastIndexOf(".")) + "-" + System.nanoTime();
			newFileName += "." + suffix;
		} else {
			newFileName = oldFileName + System.nanoTime();
		}
		
	    return newFileName;
	}
	
	//http://localhost:8080/web/resource/download?fileName=test.txt&file=E:/codetemplates.xml
	//url中不能出现 \ 只能用%2F来代替
	@RequestMapping("download")
	public ResponseEntity<byte[]> download(String fileName, File file) throws IOException { 
		String dfileName = new String(fileName.getBytes("gb2312"), "iso8859-1");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM); 
		headers.setContentDispositionFormData("attachment", dfileName);
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
		}
	
}
