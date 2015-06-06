package cn.itcast.factory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

import cn.itcast.domain.Privilege;
import cn.itcast.domain.User;
import cn.itcast.service.BusinessService;
import cn.itcast.service.impl.BusinessServiceImpl;
import cn.itcast.utils.Permission;

public class ServiceFactory {
	
	private ServiceFactory () {}
	private static ServiceFactory instance = new ServiceFactory();
	public static ServiceFactory getInstance() {
		return instance;
	}
	
	public BusinessService createService(final User user) {
		final BusinessService service = new BusinessServiceImpl();
		
		return (BusinessService) Proxy.newProxyInstance(ServiceFactory.class.getClassLoader(), service.getClass().getInterfaces(), new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args)
					throws Throwable {
				//�õ�web����õķ���
				String methodName = method.getName();
				
				//�������ʵ��������Ӧ�ķ����������ʵ���󷽷�����û��Ȩ��ע��
				Method realMethod = service.getClass().getMethod(methodName, method.getParameterTypes());
				Permission permission = realMethod.getAnnotation(Permission.class);
				if(permission == null) {
					return method.invoke(service, args);
				}
				
				//��ʵ������Ӧ�ķ�������Ȩ��ע��,��õ����ʸ÷�����Ҫ��Ȩ��
				Privilege p = new Privilege(permission.value());  //�õ�������Ҫ��Ȩ��
				
				//����û��Ƿ���Ȩ��  //AppContext ThreadLocal
				//�õ��û�����Ȩ��
				if(user == null) {
					throw new SecurityException("����û�е�¼");
				}
				
				List<Privilege> list = service.getUserAllPrivilege(user);
				if(list.contains(p)) {
					return method.invoke(service, args);
				}
				
				throw new SecurityException("��û��Ȩ��");
			}
		});
		
		
	}
}
