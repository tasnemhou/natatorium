package core;

import java.util.Map;

public interface Context {

	public abstract Map<String,Object> getDataMap();
	
	public abstract <T> T getData(String key);
	
	public abstract void  setData(String key,Object obj);
	
	public abstract Map<String,Object> getDataMapDirect();
	
	public void clear();
}
