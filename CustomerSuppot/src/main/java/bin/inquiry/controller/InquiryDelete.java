package bin.inquiry.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import bin.inquiry.control.Controller;
import bin.inquiry.dao.InquiryDAO;
import bin.inquiry.dto.InquiryDTO;
import bin.inquiry.handler.InquiryHandlerAdapter;

public class InquiryDelete implements Controller {
	private static final Log log = LogFactory.getLog(InquiryDelete.class);
	@Override
	public InquiryHandlerAdapter execute(HttpServletRequest request, HttpServletResponse response) {
		int inquiry_num = Integer.parseInt(request.getParameter("inquiry_num"));
		log.info("삭제컨트롤러" +inquiry_num);
		InquiryDAO inquiryDAO = new InquiryDAO();
		InquiryDTO inquiryDTO = new InquiryDTO();
		inquiryDTO.setInquiry_num(inquiry_num);
		request.setAttribute("inquiryDTO", inquiryDTO);
		inquiryDTO = inquiryDAO.InquiryDelete(inquiry_num);
		if (inquiryDTO == null) {
	        log.info("DTO에서 삭제가 정상적으로 처리되었습니다.");
	    } else {
	    	log.info("DTO에서 삭제가 정상적으로 처리되지 않았습니다.");
	    }
		InquiryHandlerAdapter inquiryHandlerAdapter = new InquiryHandlerAdapter();
		inquiryHandlerAdapter.setPath("/WEB-INF/inquiry/inquiry_delete_view.jsp");
		return inquiryHandlerAdapter;
	}

}
