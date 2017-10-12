package request;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;

import entity.CommonRequest;
import entity.User;

public class ReferenceResolver {
	private Map<String,TypeReference> typeRefercnceMapping; 
	
	public void init() {
		typeRefercnceMapping = new HashMap<String, TypeReference>();
		
		typeRefercnceMapping.put("loginProcess",new TypeReference<DefaultRequest<CommonRequest<User>>>() {} );
	}
	
	public TypeReference getTypeReference(String key) {
		return typeRefercnceMapping.get(key);
	}
}
