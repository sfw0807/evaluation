package com.fykj.sample.attachment.service.impl;

import org.springframework.stereotype.Service;

import com.fykj.platform.kernel.BusinessExceptionUtil;
import com.fykj.platform.kernel._c.service.ServiceSupport;
import com.fykj.platform.kernel._c.service.SingleEntityManager;
import com.fykj.platform.kernel._c.service.SingleEntityManagerGetter;
import com.fykj.sample.attachment.model.Attachment;
import com.fykj.sample.attachment.service.UploadService;

@Service
public class UploadServiceImpl extends ServiceSupport implements UploadService {
	
	private SingleEntityManager<Attachment> internalAttachmentServiceImpl
	=SingleEntityManagerGetter.get().getInstance(Attachment.class);
	
	
	@Override
	public void saveSysParam(Attachment attachment) {
		try{
			
			internalAttachmentServiceImpl.saveOnly(attachment);
		}catch(Exception e){
			BusinessExceptionUtil.throwException(e);
		}
	}

	@Override
	public void updateSysParam(Attachment attachment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteSysParam(Attachment attachment) {
		// TODO Auto-generated method stub
		
	}



}
