1.�����
	1.1 ��������
		mysql����
		dbutils���
		c3p0���ӳ�
		beanutils���
		log4j
		commons fileupload
		commons io
		jstl������
	
	1.2 ������֯����İ�
		cn.itcast.domain
		cn.itcast.dao
		cn.itcast.dao.impl
		cn.itcast.service
		cn.itcast.web.controller
		cn.itcast.utils
		

		������֯jsp��Ŀ¼��
		��WebRoot���½�managerĿ¼�������̨��ص�jsp
		1.��webroot���½�һ��manger.jspҳ�棬���ҳ������̨��ҳ�����ҳ���Ǹ�����ҳ�棬�������£�
			  <frameset rows="18%,*">
			  		<frame src="${pageContext.request.contextPath}/manager/head.jsp" name="head">
			  		<frameset  cols="15%,*">
				  		<frame src="${pageContext.request.contextPath}/manager/left.jsp" name="left">
				 		<frame src="#" name="right">
			 		</frameset>
			  </frameset>
		

		2����WebRoot���½�clientĿ¼������ǰ̨��ص�jsp
		
	3.������������Ŀ�
		create database bookstore;
		use bookstore;
		
	4.����һЩȫ�ֵĹ�����͹�����
		JdbcUtils
		WebUtils
		CharacterEncodingFilter
		HtmlFilter
		TransactionFilter
		DaoFactory
		
2.���ʵ��
	Category
		private String id;
		private String name;
		private String description;
		
	
		
	Book 
		private String id;
		private String name;
		private double price;
		private String author;
		private String image;  //��ס���ͼƬ��λ��
		private String description;
		private Category category;
		
	
	
	Order
		private String id;
		private Date ordertime;  //�µ�ʱ��
		private boolean state;   //����״̬
		private double price;    //�����ܼ�
		
		private User user;    //��ס�µ���
		private Set orderitems;   //��ס�������еĶ����� 
	
	
		
	
	OrderItem
		private String id;
		private int quantity;
		private double price;
		private Book book;   //��ס�������������ı���
		
	

	User
		private String id;
		private String username;
		private String password;
		private String phone;
		private String cellphone;
		private String email;
		private String address;
		
	
		
		
3.��Ʊ�
	create table user
	(
		id varchar(40) primary key,
		username varchar(40) not null unique,
		password varchar(40) not null,
		phone varchar(20) not null,
		cellphone varchar(20) not null,
		email varchar(40) not null,
		address varchar(255) not null
	);

	create table category
	(
		id varchar(40) primary key,
		name varchar(40) not null unique,
		description varchar(255)
	);
	
	create table book
	(
		id varchar(40) primary key,
		name varchar(40) not null unique,
		price decimal(8,2) not null,
		author varchar(40) not null,
		image varchar(255) not null,
		description varchar(255),
		category_id varchar(40),
		constraint category_id_FK foreign key(category_id) references category(id)
	);	
	
	create table orders
	(
		id varchar(40) primary key,
		ordertime datetime not null,
		state boolean not null,
		price decimal(8,2) not null,
		user_id varchar(40),
		constraint user_id_FK foreign key(user_id) references user(id)
		
	);
	
	create table orderitem
	(
		id varchar(40) primary key,
		quantity int not null,
		price decimal(8,2) not null,
		book_id varchar(40),
		constraint book_id_FK foreign key(book_id) references book(id),
		order_id varchar(40),
		constraint order_id_FK foreign key(order_id) references orders(id)
	);	

4.дdao

5.дservice

6.��web��


	
	

	
	
		
���ݿⱸ��
	create database bookstore_bak character set utf8 collate utf8_general_ci;
	use bookstore_bak;
	create table bak
	(
		id varchar(40) primary key,
		filename varchar(255) not null,
		baktime datetime not null,
		description varchar(255)
	);
		
		
		
		
		
		