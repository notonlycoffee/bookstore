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
			//����������,��ȡ����,��������,�������Ӱ󶨵���ǰ�߳�
			//JdbcUtils.startTransaction();  �ӳٵ�JdbcUtils.getConnection();�����������������ύ
			chain.doFilter(request, response);
			
			//��ȡ��ǰ�̰߳󶨵�����,�ύ����,�ر�����,�ͷ������뵱ǰ�̵߳İ�
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
