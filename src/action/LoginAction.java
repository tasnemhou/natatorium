package action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import entity.User;

@Controller
public class LoginAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	private Map<String,Object> dataMap;  

	private String loginName;
	private String password;

	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}

	public String execute(){
		dataMap = new HashMap<String, Object>();
		System.out.println("用户名名字："+loginName+"  "+password);
		dataMap.put("returnCode","-1");
		return "success";
	}
}
