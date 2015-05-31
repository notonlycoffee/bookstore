package cn.itcast.web.manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.domain.Order;
import cn.itcast.service.BusinessService;
import cn.itcast.service.impl.BusinessServiceImpl;

public class OrderServlet2 extends HttpServlet {
	private BusinessService service = new BusinessServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		if("getAll".equals(method)) {
			getAll(request,response);
		}
		if("getitem".equals(method)) {
			getitem(request,response);
		}
		if("statechange".equals(method)) {
			statechange(request,response);
		}
	}
	private void statechange(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			String id = request.getParameter("id");
			Order o = service.findOrder(id);
			boolean state = true;
			service.updateOrder(o, state);
			request.setAttribute("message", "发货成功");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "发货失败");
		}
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}
	private void getitem(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		Order o = service.findOrder(id);
		request.setAttribute("order", o);
		request.getRequestDispatcher("/manager/listitems.jsp").forward(request, response);
		
	}
	private void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean state = Boolean.parseBoolean(request.getParameter("state"));
		List list = service.getOrderByState(state);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/manager/listorder.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	}

}
