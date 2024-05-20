package bin.inquiry.dto;

public class InquiryDTO {
	private int inquiry_num;
	private String user_id;
	private String inquiry_title;
	private String inquiry_content;
	private String inquiry_date;
	private String inquiry_answer;
	private String inquiry_response_date;
	private int Inquiry_status;
	public int getInquiry_num() {
		return inquiry_num;
	}
	public void setInquiry_num(int inquiry_num) {
		this.inquiry_num = inquiry_num;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getInquiry_title() {
		return inquiry_title;
	}
	public void setInquiry_title(String inquiry_title) {
		this.inquiry_title = inquiry_title;
	}
	public String getInquiry_content() {
		return inquiry_content;
	}
	public void setInquiry_content(String inquiry_content) {
		this.inquiry_content = inquiry_content;
	}
	public String getInquiry_date() {
		return inquiry_date;
	}
	public void setInquiry_date(String inquiry_date) {
		this.inquiry_date = inquiry_date;
	}
	public String getInquiry_answer() {
		return inquiry_answer;
	}
	public void setInquiry_answer(String inquiry_answer) {
		this.inquiry_answer = inquiry_answer;
	}
	public String getInquiry_response_date() {
		return inquiry_response_date;
	}
	public void setInquiry_response_date(String inquiry_response_date) {
		this.inquiry_response_date = inquiry_response_date;
	}
	public int getInquiry_status() {
		return Inquiry_status;
	}
	public void setInquiry_status(int inquiry_status) {
		this.Inquiry_status = inquiry_status;
	}
	@Override
	public String toString() {
		return "InquiryDTO [inquiry_num=" + inquiry_num + ", user_id=" + user_id + ", inquiry_title=" + inquiry_title
				+ ", inquiry_content=" + inquiry_content + ", inquiry_date=" + inquiry_date + ", inquiry_answer="
				+ inquiry_answer + ", inquiry_response_date=" + inquiry_response_date + ", Inquiry_status="
				+ Inquiry_status + "]";
	}
	
}
