package cn.itcast.service.impl;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import cn.itcast.dao.BookDao;
import cn.itcast.dao.CategoryDao;
import cn.itcast.dao.DbbakDao;
import cn.itcast.dao.OrderDao;
import cn.itcast.dao.UserDao;
import cn.itcast.domain.Book;
import cn.itcast.domain.Cart;
import cn.itcast.domain.CartItem;
import cn.itcast.domain.Category;
import cn.itcast.domain.Dbbak;
import cn.itcast.domain.Order;
import cn.itcast.domain.OrderItem;
import cn.itcast.domain.PageBean;
import cn.itcast.domain.QueryInfo;
import cn.itcast.domain.QueryResult;
import cn.itcast.domain.User;
import cn.itcast.factory.DaoFactory;
import cn.itcast.service.BusinessService;
import cn.itcast.utils.Permission;

public class BusinessServiceImpl implements BusinessService {

	private BookDao bdao = DaoFactory.getInstances().createDao(BookDao.class);
	private CategoryDao cdao = DaoFactory.getInstances().createDao(CategoryDao.class);
	private OrderDao odao = DaoFactory.getInstances().createDao(OrderDao.class);
	private UserDao udao = DaoFactory.getInstances().createDao(UserDao.class);
	private DbbakDao ddao = DaoFactory.getInstances().createDao(DbbakDao.class);
	
	
	
	/* (non-Javadoc)
	 * @see cn.itcast.service.impl.BusinessService#addCategory(cn.itcast.domain.Category)
	 */
	
	@Permission("添加分类")
	@Override
	public void addCategory(Category c) {
		cdao.add(c);
	}
	
	/* (non-Javadoc)
	 * @see cn.itcast.service.impl.BusinessService#findCategory(java.lang.String)
	 */
	@Override
	public Category findCategory(String id) {
		return cdao.find(id);
	}
	
	/* (non-Javadoc)
	 * @see cn.itcast.service.impl.BusinessService#getAllCategory()
	 */
	@Permission("查看分类")
	@Override
	public List<Category> getAllCategory() {
		return cdao.getAll();
	}
	
	
	
	/* (non-Javadoc)
	 * @see cn.itcast.service.impl.BusinessService#addBook(cn.itcast.domain.Book)
	 */
	
	@Override
	public void addBook(Book book) {
		bdao.add(book);
	}
	
	/* (non-Javadoc)
	 * @see cn.itcast.service.impl.BusinessService#findBook(java.lang.String)
	 */
	@Override
	public Book findBook(String id) {
		return bdao.find(id);
	}
	
	/* (non-Javadoc)
	 * @see cn.itcast.service.impl.BusinessService#bookPageQuery(cn.itcast.domain.QueryInfo)
	 */
	@Override
	public PageBean bookPageQuery(QueryInfo info) {
		QueryResult result = bdao.pageQuery(info.getStartIndex(), info.getPageSize(), info.getWhere(), info.getQueryValue());
		PageBean bean = new PageBean();
		bean.setCurrentPage(info.getCurrentPage());
		bean.setList(result.getList());
		bean.setPageSize(info.getPageSize());
		bean.setTotalRecord(result.getTotalRecord());
		return bean;
	}
	
	public List<Book> getAllBook() {
		return bdao.getAll();
	}
	
	
	/* (non-Javadoc)
	 * @see cn.itcast.service.impl.BusinessService#addUser(cn.itcast.domain.User)
	 */
	
	@Override
	public void addUser(User u) {
		udao.add(u);
	}
	
	/* (non-Javadoc)
	 * @see cn.itcast.service.impl.BusinessService#findUser(java.lang.String)
	 */
	@Override
	public User findUser(String id) {
		return udao.find(id);
	}
	
	/* (non-Javadoc)
	 * @see cn.itcast.service.impl.BusinessService#findUser(java.lang.String, java.lang.String)
	 */
	@Override
	public User findUser(String username, String password) {
		return udao.find(username, password);
	}
	
	/* (non-Javadoc)
	 * @see cn.itcast.service.impl.BusinessService#saveOrder(cn.itcast.domain.Cart, cn.itcast.domain.User)
	 */
	
	@Override
	public void saveOrder(Cart cart, User user) {
		
		Order order = new Order();
		order.setId(UUID.randomUUID().toString());
		order.setOrdertime(new Date());
		order.setPrice(cart.getPrice());
		order.setState(false);
		order.setUser(user);
		
		Set cartitemset = new LinkedHashSet();
		
		Map<String, CartItem> map = cart.getMap();
		
		for(Map.Entry<String, CartItem> entry : map.entrySet()) {
			CartItem citem = entry.getValue();
			
			OrderItem oitem = new OrderItem();
			
			
			oitem.setBook(citem.getBook());
			oitem.setId(UUID.randomUUID().toString());
			oitem.setPrice(citem.getPrice());
			oitem.setQuantity(citem.getQuantity());
			
			cartitemset.add(oitem);
		}
		
		order.setOrderitems(cartitemset);
		
		odao.add(order);
	}
	
	/* (non-Javadoc)
	 * @see cn.itcast.service.impl.BusinessService#findOrder(java.lang.String)
	 */
	@Override
	public Order findOrder(String id) {
		return odao.find(id);
	}
	
	/* (non-Javadoc)
	 * @see cn.itcast.service.impl.BusinessService#getOrderByState(boolean)
	 */
	@Override
	public List<Order> getOrderByState(boolean state) {
		return odao.getAll(state);
	}
	
	public void updateOrder(Order o , boolean state) {
		odao.update(o, state);
	}
	
	
	public List<Order> getOrderByUser(User user) {
		return odao.getAllByUser(user);
	}
	
	
	
	public void addDbbak(Dbbak bak) {
		ddao.add(bak);
	}
	
	public List<Dbbak> getAllDbbak() {
		return ddao.getAll();
	}
	
	public Dbbak findDbbak(String id) {
		return ddao.find(id);
	}

	@Override
	public List getUserAllPrivilege(User user) {
		return udao.getAllPrivilege(user);
	}
	  
}
