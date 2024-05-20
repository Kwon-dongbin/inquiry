package bin.inquiry.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import bin.inquiry.control.Controller;
import bin.inquiry.dao.InquiryDAO;
import bin.inquiry.dto.InquiryDTO;
import bin.inquiry.handler.InquiryHandlerAdapter;

public class InquiryList implements Controller {

private static final Log log = LogFactory.getLog(InquiryList.class);
	@Override
	public InquiryHandlerAdapter execute(HttpServletRequest request, HttpServletResponse response) {
		InquiryDAO inquiryDAO = new InquiryDAO();
		InquiryDTO inquiryDTO = new InquiryDTO();
		log.info("전체 데이터 확인 : " + inquiryDTO);
		ArrayList<InquiryDTO> arrayList = inquiryDAO.InquiryList();
		log.info("컨트롤러의 DTO 값 확인 " + arrayList);
		request.setAttribute("arrayList", arrayList);
		request.setAttribute("listSize", arrayList.size());
		InquiryHandlerAdapter inquiryHandlerAdapter = new InquiryHandlerAdapter();
		log.info("컨트롤러 문의 글 전체 조회");
		inquiryHandlerAdapter.setPath("/WEB-INF/inquiry/inquiry_list.jsp");
		return inquiryHandlerAdapter;
	}

}
