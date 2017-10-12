package interceptor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

import common.NATAConstants;
import core.Context;
import core.DefaultContext;
import entity.CommonRequest;
import entity.User;
import request.DefaultRequest;
import request.ReferenceResolver;
import request.RequestBody;
import request.RequestHead;

public class ContextInterceptor implements Interceptor {
	private static final long serialVersionUID = 3244973830196015811L;
		  
	private Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private ReferenceResolver typeReferenceResolver;
	@Autowired
	private ObjectMapper jsonObjectMapper;
	
	@Override
	public void destroy() {
		logger.info("================================》拦截器销毁！");
	}
	@Override
	public void init() {
		logger.info("========================》拦截器初始化！");
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Context context = new DefaultContext();
		//获取请求路径
		String servletPath = ServletActionContext.getRequest().getServletPath();
		String path = servletPath.substring(1,servletPath.length());
		//获取前台报文
		String[] jsonRequest = (String[])invocation.getInvocationContext().getParameters().get("str");
		logger.info("JSON请求报文:"+jsonRequest[0]);
		//解析 将json报文转为对应的实体
		Object obj = transform(jsonRequest[0],path);
//		将请求报文放入context
		requestContext(obj,context);
		
		String result = invocation.invoke();
		
		//组装应答报文
		Map resMap = new HashMap();
		Map headMap = new HashMap();
		String errorMessage = null;
		if(StringUtils.isNotBlank(errorMessage)) {
			headMap.put("ERROR_MESSAGE",errorMessage);
		}
		resMap.put("RSP_HEAD",headMap);
		resMap.put("RSP_BODY",context.getDataMap());
		logger.info(resMap);
		return result;
	}
	
	public Object transform(String jsonRequest,String process) throws Exception  {
		try {
			Object bean = null;
			if(typeReferenceResolver.getTypeReference(process)==null) {
				bean = jsonObjectMapper.readValue(jsonRequest,java.util.Map.class);
			}else {
				bean = jsonObjectMapper.readValue(jsonRequest,typeReferenceResolver.getTypeReference(process));
			}
			return bean;
		}catch (JsonParseException e) {
			logger.error("",e);
			throw new Exception("报文不符合JSON规范");
		}catch(JsonMappingException e) {
			logger.error("",e);
			throw new Exception("JSON报文转换成Request对象异常");
		}catch(IOException e) {
			logger.error("",e);
			throw new Exception("JSON报文解析异常");
		}
	}
	
	public void requestContext(Object obj,Context context) {
		if(obj instanceof java.util.Map) {
			resolveMap(obj,context.getDataMap());
		}else {
			DefaultRequest request = (DefaultRequest)obj;
			//将请求报文的head部分放入context
			RequestHead requestHead = request.getRequestHead();
			context.setData(NATAConstants.REQUEST_HEAD,requestHead);
			//将请求报文的body部分放入context
			CommonRequest requestBody = (CommonRequest) request.getRequestBody();
			context.setData(NATAConstants.REQUEST_BODY,requestBody);
			//将请求报文的实体（bean）部分放入context
			Object requestBean = requestBody.getRequestBean();
			context.setData(NATAConstants.REQUEST_BEAN, requestBean);
		}
	}
	
	/**
	 * 	将所在map放到顶层
	 * @param object
	 * @param targetMap
	 */
	public void resolveMap(Object object,Map<String,Object>targetMap) {
		Map<String,Object> toHandleMap = (Map<String, Object>) object;
		for(String key:toHandleMap.keySet()) {
			Object obj = toHandleMap.get(key);
			if(obj instanceof java.util.Map) {
				resolveMap(obj,targetMap);
			}else {
				targetMap.put(key, obj);
			}
		}
		
	}
}
