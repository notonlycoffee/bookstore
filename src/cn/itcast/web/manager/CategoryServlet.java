package cn.itcast.web.manager;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.domain.Category;
import cn.itcast.domain.User;
import cn.itcast.factory.ServiceFactory;
import cn.itcast.service.BusinessService;
import cn.itcast.service.impl.BusinessServiceImpl;
import cn.itcast.utils.WebUtils;

public class CategoryServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String method = request.getParameter("method");
		if("add".equals(method)) {
			add(request,response);
		}
		if("getAll".equals(method)) {
			getAll(request,response);
		}
		
	}

	private void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BusinessService service = ServiceFactory.getInstance().createService((User) request.getSession().getAttribute("user"));
		try {
			List list = service.getAllCategory();  //---->invoke(throw SecurityException)
			request.setAttribute("list", list);
			request.getRequestDispatcher("/manager/listcategory.jsp").forward(request, response);
		} catch(SecurityException e) {
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		} catch(Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BusinessService service = ServiceFactory.getInstance().createService((User) request.getSession().getAttribute("user"));
		try {
			Category c = WebUtils.request2bean(request, Category.class);
			c.setId(UUID.randomUUID().toString());
			service.addCategory(c);
			request.setAttribute("message", "Ìí¼Ó³É¹¦");
		} catch (SecurityException e) {
				request.setAttribute("message", e.getMessage());
		} catch(Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	}

}
