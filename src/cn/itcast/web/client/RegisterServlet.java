package cn.itcast.web.client;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.domain.User;
import cn.itcast.service.BusinessService;
import cn.itcast.service.impl.BusinessServiceImpl;
import cn.itcast.utils.WebUtils;

public class RegisterServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			User user = WebUtils.request2bean(request, User.class);
			BusinessService service = new BusinessServiceImpl();
			user.setId(UUID.randomUUID().toString());
			service.addUser(user);

			request.setAttribute("message", "×¢²á³É¹¦!!!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "×¢²áÊ§°Ü!!!");
		}
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	}

}
