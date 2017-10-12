package request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestHead {

	@JsonProperty
	private String TRAN_PROCESS;
	@JsonProperty
	private String TRAN_ID;
	
	public String getTRAN_PROCESS() {
		return TRAN_PROCESS;
	}
	public void setTRAN_PROCESS(String tRAN_PROCESS) {
		TRAN_PROCESS = tRAN_PROCESS;
	}
	public String getTRAN_ID() {
		return TRAN_ID;
	}
	public void setTRAN_ID(String tRAN_ID) {
		TRAN_ID = tRAN_ID;
	}
}
