package cn.itcast.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import cn.itcast.utils.JdbcUtils;

import com.mchange.v2.c3p0.codegen.JdbcProxyGenerator;

public class TransactionFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		try {
			//拦截下来后,获取连接,开启事务,并把链接绑定到当前线程
			//JdbcUtils.startTransaction();  延迟到JdbcUtils.getConnection();方法里面设置事务提交
			chain.doFilter(request, response);
			
			//获取当前线程绑定的连接,提交事务,关闭连接,释放连接与当前线程的绑定
			JdbcUtils.commitTransaction();
		} catch(Exception e) {
			JdbcUtils.closeConnection();
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
