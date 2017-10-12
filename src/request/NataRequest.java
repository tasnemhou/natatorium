package request;

public interface NataRequest<T> {

	public RequestHead getRequestHead();
	
	public T getRequestBody();
}
