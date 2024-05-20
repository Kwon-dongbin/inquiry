package bin.inquiry.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import bin.inquiry.control.Controller;
import bin.inquiry.dao.InquiryDAO;
import bin.inquiry.dto.InquiryDTO;
import bin.inquiry.handler.InquiryHandlerAdapter;

public class InquiryAnswerWriteView implements Controller{

private static final Log log = LogFactory.getLog(InquiryAnswerWriteView.class);
	@Override
	public InquiryHandlerAdapter execute(HttpServletRequest request, HttpServletResponse response) {
		int inquiry_num = Integer.parseInt(request.getParameter("inquiry_num"));
		log.info("DB에 저장된 글의 고유번호 : " + inquiry_num);
		String inquiry_answer = request.getParameter("inquiry_answer");
		log.info("답변 내용 : " +inquiry_answer);
		InquiryDAO inquiryDAO = new InquiryDAO( );
		InquiryDTO inquiryDTO = new InquiryDTO( );
		inquiryDTO.setInquiry_num(inquiry_num);
		inquiryDTO.setInquiry_answer(inquiry_answer);
		inquiryDTO = inquiryDAO.InquiryAnswerWrite(inquiryDTO);
		log.info(inquiryDTO);
		request.setAttribute("inquiryDTO", inquiryDTO);
		InquiryHandlerAdapter inquiryHandlerAdapter = new InquiryHandlerAdapter( );
		inquiryHandlerAdapter.setPath("/WEB-INF/inquiry/inquiry_answer_write_view.jsp");
		return inquiryHandlerAdapter;
	}

}
