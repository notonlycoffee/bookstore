package cn.itcast.web.client;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.domain.Cart;
import cn.itcast.domain.Order;
import cn.itcast.domain.User;
import cn.itcast.service.BusinessService;
import cn.itcast.service.impl.BusinessServiceImpl;

public class OrderServlet extends HttpServlet {
	private BusinessService service = new BusinessServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if(user == null) {
			request.setAttribute("message","请先登录");
			request.getRequestDispatcher("/message.jsp").forward(request,response);
			return ;
		}
		String method = request.getParameter("method");
		if("generateorder".equals(method)) {
			generateorder(request,response,user);
		}
		if("personalorder".equals(method)) {
			personalorder(request,response,user);
		}
		
		
	}

	private void personalorder(HttpServletRequest request,HttpServletResponse response, User user) throws ServletException, IOException {
		List<Order> list = service.getOrderByUser(user);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/client/personalorder.jsp").forward(request, response);
	}

	private void generateorder(HttpServletRequest request,HttpServletResponse response, User user) throws ServletException, IOException {
		try {
			Cart cart = (Cart) request.getSession().getAttribute("cart");
			
			service.saveOrder(cart, user);
			

			request.setAttribute("message", "生成订单成功");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "失败");
		}
		request.getRequestDispatcher("/message.jsp").forward(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	}

}
