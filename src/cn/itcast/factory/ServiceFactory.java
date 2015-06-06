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
				//得到web层调用的方法
				String methodName = method.getName();
				
				//反射出真实对象上相应的方法，检查真实对象方法上有没有权限注解
				Method realMethod = service.getClass().getMethod(methodName, method.getParameterTypes());
				Permission permission = realMethod.getAnnotation(Permission.class);
				if(permission == null) {
					return method.invoke(service, args);
				}
				
				//真实对象相应的方法上有权限注解,则得到访问该方法需要的权限
				Privilege p = new Privilege(permission.value());  //得到方法需要的权限
				
				//检查用户是否有权限  //AppContext ThreadLocal
				//得到用户所有权限
				if(user == null) {
					throw new SecurityException("您还没有登录");
				}
				
				List<Privilege> list = service.getUserAllPrivilege(user);
				if(list.contains(p)) {
					return method.invoke(service, args);
				}
				
				throw new SecurityException("您没有权限");
			}
		});
		
		
	}
}
