package cn.itcast.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CharacterEncodingFilter implements Filter {

	private FilterConfig filterConfig;
	private String defaultcharset = "UTF-8";
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		String charset = filterConfig.getInitParameter("charset");
		
		if( charset == null) {
			request.setCharacterEncoding(defaultcharset);
		} else {
			request.setCharacterEncoding(charset);
		}
		
		chain.doFilter(request, response);
		

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
