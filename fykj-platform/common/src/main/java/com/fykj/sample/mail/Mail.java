package com.fykj.sample.mail;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("mail")
@SuppressWarnings("serial")
public class Mail implements Serializable {  

	 public static final String ENCODEING = "UTF-8";  
	  
	 	@Value("${mail.host}")
	    private String host; // 服务器地址  
	  
		@Value("${mail.sender}")
	    private String sender; // 发件人的邮箱  
	  
	    private String receiver; // 收件人的邮箱  
	  
	    private String name; // 发件人昵称  
	  
	    @Value("${mail.username}")
	    private String username; // 账号  
	  
	    @Value("${mail.password}")
	    private String password; // 密码  
	  
	    @Value("${mail.subject}")
	    private String subject; // 主题  
	  
	    @Value("${mail.message}")
	    private String message; // 默认信息
	    
	    @Value("${mail.timeout}")
	    private int timeOut;//邮件过多少小时超时
	    
	    private String sendMessage;//发送的信息
	  
	    public String getHost() {  
	        return host;  
	    }  
	  
	    public void setHost(String host) {  
	        this.host = host;  
	    }  
	  
	    public String getSender() {  
	        return sender;  
	    }  
	  
	    public void setSender(String sender) {  
	        this.sender = sender;  
	    }  
	  
	    public String getReceiver() {  
	        return receiver;  
	    }  
	  
	    public void setReceiver(String receiver) {  
	        this.receiver = receiver;  
	    }  
	  
	    public String getName() {  
	        return name;  
	    }  
	  
	    public void setName(String name) {  
	        this.name = name;  
	    }  
	  
	    public String getUsername() {  
	        return username;  
	    }  
	  
	    public void setUsername(String username) {  
	        this.username = username;  
	    }  
	  
	    public String getPassword() {  
	        return password;  
	    }  
	  
	    public void setPassword(String password) {  
	        this.password = password;  
	    }  
	  
	    public String getSubject() {  
	        return subject;  
	    }  
	  
	    public void setSubject(String subject) {  
	        this.subject = subject;  
	    }  
	  
	    public String getMessage() {  
	        return message;  
	    }  
	  
	    public void setMessage(String message) {  
	        this.message = message;  
	    }

		public String getSendMessage() {
			return sendMessage;
		}

		public void setSendMessage(String sendMessage) {
			this.sendMessage = sendMessage;
		}

		public int getTimeOut() {
			return timeOut;
		}

		public void setTimeOut(int timeOut) {
			this.timeOut = timeOut;
		}  
	    
	    
}
