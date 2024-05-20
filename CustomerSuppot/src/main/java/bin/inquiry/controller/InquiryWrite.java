package bin.inquiry.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import bin.inquiry.control.Controller;
import bin.inquiry.dao.InquiryDAO;
import bin.inquiry.dto.InquiryDTO;
import bin.inquiry.handler.InquiryHandlerAdapter;

public class InquiryWrite implements Controller {

	private static final Log log = LogFactory.getLog(InquiryWrite.class);

	@Override
	public InquiryHandlerAdapter execute(HttpServletRequest request, HttpServletResponse response) {
		String inquiry_title = request.getParameter("inquiry_title");
		String inquiry_content = request.getParameter("inquiry_content");
		
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("user_id");
		
		InquiryDTO inquiryDTO = new InquiryDTO();
		inquiryDTO.setInquiry_title(inquiry_title);
		inquiryDTO.setInquiry_content(inquiry_content);
		inquiryDTO.setUser_id(user_id);
		
		InquiryDAO inquiryDAO = new InquiryDAO();
		inquiryDTO = inquiryDAO.InquiryWrite(inquiryDTO);
		
		ArrayList<InquiryDTO> arrayList = inquiryDAO.InquiryList();
		request.setAttribute("arrayList", arrayList);
		request.setAttribute("inquiryDTO", inquiryDTO);
		
		log.info("글 쓰기");
		
		InquiryHandlerAdapter inquiryHandlerAdapter = new InquiryHandlerAdapter();
		inquiryHandlerAdapter.setPath("/WEB-INF/inquiry/inquiry_write_view.jsp");
		
		return inquiryHandlerAdapter;
	}
}