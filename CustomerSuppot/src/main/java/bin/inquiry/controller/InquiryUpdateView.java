package bin.inquiry.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import bin.inquiry.control.Controller;
import bin.inquiry.dao.InquiryDAO;
import bin.inquiry.dto.InquiryDTO;
import bin.inquiry.handler.InquiryHandlerAdapter;

public class InquiryUpdateView implements Controller {
	private static final Log log = LogFactory.getLog(InquiryUpdateView.class);
	@Override
	public InquiryHandlerAdapter execute(HttpServletRequest request, HttpServletResponse response) {
		int inquiry_num = Integer.parseInt(request.getParameter("inquiry_num"));
		log.info("DB에 저장된 글의 고유번호 : " + inquiry_num);
		String inquiry_title = request.getParameter("inquiry_title");
		log.info("수정된 제목 : " + inquiry_title);
		String inquiry_content = request.getParameter("inquiry_content");
		log.info("수정된 내용 : " + inquiry_content);
		InquiryDAO inquiryDAO = new InquiryDAO( );
		InquiryDTO inquiryDTO = new InquiryDTO( );
		inquiryDTO.setInquiry_num(inquiry_num);
		inquiryDTO.setInquiry_title(inquiry_title);
		inquiryDTO.setInquiry_content(inquiry_content);
		inquiryDTO = inquiryDAO.InquiryUpdate(inquiryDTO);
		log.info("수정된 DTO 확인 : " + inquiryDTO);
		request.setAttribute("inquiryDTO", inquiryDTO);
		InquiryHandlerAdapter inquiryHandlerAdapter = new InquiryHandlerAdapter( );
		inquiryHandlerAdapter.setPath("/WEB-INF/inquiry/inquiry_update_view.jsp");
		return inquiryHandlerAdapter;
	}

}
