package bin.inquiry.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bin.inquiry.handler.InquiryHandlerAdapter;

public interface Controller {
	public InquiryHandlerAdapter execute(HttpServletRequest request, HttpServletResponse response);
}
