package bin.inquiry.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import bin.inquiry.control.Controller;
import bin.inquiry.dao.InquiryDAO;
import bin.inquiry.dto.InquiryDTO;
import bin.inquiry.handler.InquiryHandlerAdapter;

public class InquiryAnswerWrite implements Controller {

private static final Log log = LogFactory.getLog(InquiryAnswerWrite.class);
	@Override
	public InquiryHandlerAdapter execute(HttpServletRequest request, HttpServletResponse response) {
		int inquiry_num = Integer.parseInt(request.getParameter("inquiry_num"));
		log.info("DB에 저장된 글의 고유번호 : " + inquiry_num);
		InquiryDAO inquiryDAO = new InquiryDAO();
		InquiryDTO inquiryDTO = new InquiryDTO();
		inquiryDTO = inquiryDAO.InquirySelect(inquiry_num);
		request.setAttribute("inquiryDTO", inquiryDTO);
		InquiryHandlerAdapter inquiryHandlerAdapter = new InquiryHandlerAdapter();
		log.info("DB에 저장된 " +inquiry_num+"번 글 조회");
		inquiryHandlerAdapter.setPath("/WEB-INF/inquiry/inquiry_answer_write.jsp");
		return inquiryHandlerAdapter;
	}

}
