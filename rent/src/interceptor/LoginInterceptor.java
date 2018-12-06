package interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

import entity.User;

public class LoginInterceptor extends MethodFilterInterceptor{

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		System.out.println("ִ����������������������");
		//��ȡsession��ֵ
		User user= (User) ActionContext.getContext().getSession().get("user");
		System.out.println("��������"+user);
		String str="";
		if(user==null){
			System.out.println("����û�е�¼�����ȵ�¼��");
			return "ok2";
		}
		str=invocation.invoke();
		return str;
	}

}
