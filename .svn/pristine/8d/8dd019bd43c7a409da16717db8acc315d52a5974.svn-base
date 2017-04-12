package com.fykj.sample.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.fykj.platform.kernel._c.ServerSessionHolder;
import com.fykj.platform.kernel._c.model.InvokeResult;
import com.fykj.platform.util.JJSON;

public class SessionUserFilter implements Filter{
	
	private String[] exceptUrls;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String exceptUrl = filterConfig.getInitParameter("exceptUrl");
		if(StringUtils.isNotBlank(exceptUrl)){
			exceptUrls = exceptUrl.split(",");
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		WebApplicationContext applicationContext = 
				WebApplicationContextUtils.getWebApplicationContext(
				request.getServletContext());
		
		//Object object=applicationContext.getBean("_SysParamServiceImpl");
		//System.out.println(object);
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String url = req.getServletPath();
		if(url.indexOf("/ui/") != -1){
			chain.doFilter(request, response);
		}else if(ArrayUtils.isNotEmpty(exceptUrls) && ArrayUtils.contains(exceptUrls, url)){
			chain.doFilter(request, response);
		}else{
			if(!ServerSessionHolder.isLogin()){
//				String requestType = req.getHeader("X-Requested-With");
//				if(StringUtils.isNotBlank(requestType)){
//					res.sendError(400);
//				}else{
//					res.sendRedirect(req.getContextPath());
//				}
				res.getWriter().write(JJSON.get().formatObject(InvokeResult
						.failure("LOGIN NEED!")));
				
			}else{
				chain.doFilter(request, response);
			}
		}
	}

	@Override
	public void destroy() {
		
	}

}
