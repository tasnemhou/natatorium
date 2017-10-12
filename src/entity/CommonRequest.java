package entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommonRequest<T> {

//	岗位信息
//	@JsonProperty("REQ_POST")
//	private Post post;
	
//	请求bean参数
	@JsonProperty("REQ_BEAN")
	private T requestBean;

/*	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}*/

	public T getRequestBean() {
		return requestBean;
	}

	public void setRequestBean(T requestBean) {
		this.requestBean = requestBean;
	}
	
	
}
