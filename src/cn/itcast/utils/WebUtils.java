package cn.itcast.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.itcast.domain.Book;
import cn.itcast.domain.Category;
import cn.itcast.service.BusinessService;
import cn.itcast.service.impl.BusinessServiceImpl;

public class WebUtils {

	public static <T> T request2bean(HttpServletRequest request, Class beanclass) {
		try {
			T bean = (T) beanclass.newInstance();
			Map map = request.getParameterMap();
			BeanUtils.populate(bean, map);
			return bean;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public static Book uploadBook(HttpServletRequest request, String uppath) throws FileSizeLimitExceededException {

		try {
			Book book = new Book();
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setFileSizeMax(1024 * 1024 * 20);
			upload.setHeaderEncoding(request.getCharacterEncoding());
			List<FileItem> list = upload.parseRequest(request);
			for (FileItem item : list) {
				if (item.isFormField()) {
					String name = item.getFieldName();
					String value = item.getString(request.getCharacterEncoding());
					if("category_id".equals(name)) {
						BusinessService service = new BusinessServiceImpl();
						Category c = service.findCategory(value);
						
						book.setCategory(c);
					} else {
						BeanUtils.setProperty(book, name, value);
					}
					
				} else {

					String fileName = item.getName();//要考虑到浏览器兼容性问题,有些事发挥文件的完整名称(包括父亲目录的),有些是直接返回文件名
					fileName = fileName.substring(fileName.lastIndexOf("\\")+1);  
					String savePath = uppath;
					String saveFileName = UUID.randomUUID().toString() + fileName;
					

					InputStream in = item.getInputStream();
					OutputStream out = new FileOutputStream(new File(savePath+ File.separator + saveFileName));
					int len = 0;
					byte[] buff = new byte[1024];
					while ((len = in.read(buff)) > 0) {
						out.write(buff,0,len);
					}
					in.close();
					out.close();
					item.delete();

					book.setImage(saveFileName);
				}
			}
			book.setId(UUID.randomUUID().toString());
			
			return book;
		} catch (FileUploadBase.FileSizeLimitExceededException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
