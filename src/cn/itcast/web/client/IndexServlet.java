package cn.itcast.web.client;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.domain.Category;
import cn.itcast.domain.PageBean;
import cn.itcast.domain.QueryInfo;
import cn.itcast.service.impl.BusinessServiceImpl;
import cn.itcast.utils.WebUtils;

public class IndexServlet extends HttpServlet {

	private BusinessServiceImpl service = new BusinessServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		QueryInfo info = WebUtils.request2bean(request, QueryInfo.class);
		
		List<Category> categories = service.getAllCategory();
		
		String category_id = request.getParameter("category_id");
		if( category_id != null && !category_id.trim().equals("")) {
			info.setQueryName("category_id");
			info.setQueryValue(category_id);
		}
		PageBean pagebean = service.bookPageQuery(info);
		request.setAttribute("categories", categories);
		request.setAttribute("pagebean", pagebean);
		
		request.getRequestDispatcher("/client/index.jsp").forward(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	}

}
