package request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DefaultRequest<T> implements NataRequest<T> {

	@JsonProperty("REQ_HEAD")
	private RequestHead requestHead;
	@JsonProperty("REQ_BODY")
	private T requestBody;
	
	@Override
	public RequestHead getRequestHead() {
		// TODO Auto-generated method stub
		return requestHead;
	}

	@Override
	public T getRequestBody() {
		// TODO Auto-generated method stub
		return requestBody;
	}

	public void setRequestHead(RequestHead requestHead) {
		this.requestHead = requestHead;
	}

	public void setRequestBody(T requestBody) {
		this.requestBody = requestBody;
	}
	
}
