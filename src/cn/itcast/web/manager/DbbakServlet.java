package cn.itcast.web.manager;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.domain.Dbbak;
import cn.itcast.service.BusinessService;
import cn.itcast.service.impl.BusinessServiceImpl;

public class DbbakServlet extends HttpServlet {

	private BusinessService service = new BusinessServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String method = request.getParameter("method");
		if("backup".equals(method)) {
			backup(request,response);
		}
		if("list".equals(method)) {
			list(request,response);
		}
		if("restore".equals(method)) {
			restore(request,response);
		}
	}

	private void restore(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		try {

			String id = request.getParameter("id");
			Dbbak bak = service.findDbbak(id);
			String filename = bak.getFilename();
			
			String command = "cmd /c mysql -uroot -proot bookstore<" + filename;
			Runtime.getRuntime().exec(command);

			request.setAttribute("message", "恢复成功");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "恢复失败");
		}
		request.getRequestDispatcher("/message.jsp").forward(request, response);
		
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Dbbak> list = service.getAllDbbak();
		request.setAttribute("list", list);
		request.getRequestDispatcher("/manager/listdbbak.jsp").forward(request,response);
	}

	private void backup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try {
			String bakpath = this.getServletContext().getRealPath("/bakup");
			String filename = System.currentTimeMillis() + ".sql";
			
			String command = "cmd /c mysqldump -uroot -proot bookstore>" + bakpath + "\\" + filename;  //windows
			Runtime.getRuntime().exec(command);
			
			Dbbak bak = new Dbbak();
			bak.setBaktime(new Date());
			bak.setDescription(request.getParameter("description"));
			bak.setFilename(bakpath + "\\" + filename);
			bak.setId(UUID.randomUUID().toString());
			service.addDbbak(bak);
			request.setAttribute("message", "备份成功!!!");
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "备份失败!!!");
		}
		request.getRequestDispatcher("/message.jsp").forward(request, response);
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	}

}
