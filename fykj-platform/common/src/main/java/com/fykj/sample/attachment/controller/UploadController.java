package com.fykj.sample.attachment.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fykj.platform.kernel._c.model.InvokeResult;
import com.fykj.platform.kernel.parameter.annotation.ParamValidation4Controller;
import com.fykj.platform.util.Copy;
import com.fykj.platform.web.model.UploadFileVO;
import com.fykj.sample.attachment.model.Attachment;
import com.fykj.sample.attachment.service.UploadService;
import com.fykj.sample.attachment.vo.AttachmentOutVO;

/**
 * 
 * @author 张军
 *
 */
@Controller
@RequestMapping("/upload")
@ParamValidation4Controller
public class UploadController {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(UploadController.class);
	
	@Autowired
	private UploadService uploadService;
	
	@ResponseBody
	@RequestMapping("/file")
	public InvokeResult file(UploadFileVO[] uploadFileVOs) {
		try {
			if (uploadFileVOs.length<1) {
				return InvokeResult.failure("上传文件出错，附件不存在");
			}
			List<AttachmentOutVO> attachmentOutVos = new ArrayList<AttachmentOutVO>();
			Attachment attachment;
			for (UploadFileVO uploadFileVO : uploadFileVOs) {
				 attachment = buileAttachment(uploadFileVO);
				 String fileName = uploadFileVO.getFileName();
				 uploadService.saveSysParam(attachment);
				 AttachmentOutVO attachmentOutVO = Copy.simpleCopy(attachment, AttachmentOutVO.class);
				 attachmentOutVO.setFileName(fileName);
				 attachmentOutVos.add(attachmentOutVO);
			}
			
			return InvokeResult.success(attachmentOutVos);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure("上传文件出错");
		}
	}
	
	private Attachment buileAttachment(UploadFileVO uploadFileVO) {
		Attachment simpleCopy = new Attachment();
		simpleCopy.setName(uploadFileVO.getFileNameNoExtension());
		simpleCopy.setRealName(uploadFileVO.getExpectedFullFileName());
		simpleCopy.setPath(uploadFileVO.getRelativePath());
		simpleCopy.setSuffixation(uploadFileVO.getFileExtension());
		
		return simpleCopy;
	}


}
