package request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestBody {
	@JsonProperty
	private Object REQ_BEAN;
//	private Object REQ_POST;

	public Object getREQ_BEAN() {
		return REQ_BEAN;
	}

	public void setREQ_BEAN(Object rEQ_BEAN) {
		REQ_BEAN = rEQ_BEAN;
	}
	
}
