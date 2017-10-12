package core;

import java.util.HashMap;
import java.util.Map;

public class DefaultContext implements Context {

	private Map<String,Object> dataMap = new HashMap<String,Object>();
	
	@Override
	public Map<String, Object> getDataMap() {
		// TODO Auto-generated method stub
		return this.dataMap;
	}

	@Override
	public <T> T getData(String key) {
		// TODO Auto-generated method stub
		return (T)this.dataMap.get(key);
	}

	@Override
	public void setData(String key, Object obj) {
		// TODO Auto-generated method stub
		this.dataMap.put(key, obj);
	}

	@Override
	public Map<String, Object> getDataMapDirect() {
		// TODO Auto-generated method stub
		return this.dataMap;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		this.dataMap.clear();
	}

}
