package com.fykj.platform.kernel._c;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import com.fykj.platform.kernel._c.model.JModel;
import com.fykj.platform.kernel._c.model.SessionUser;

public class ServerSession extends HashMap<String, Object> implements JModel {

	private SessionUser sessionUser;

	private transient HttpSession httpSession;
	
	public SessionUser getSessionUser() {
		return sessionUser;
	}
	
	void setSessionUser(SessionUser sessionUser) {
		this.sessionUser = sessionUser;
	}
	
	void setHttpSession(HttpSession httpSession) {
		this.httpSession = httpSession;
	}
	
	HttpSession getHttpSession() {
		return httpSession;
	}
	
}
