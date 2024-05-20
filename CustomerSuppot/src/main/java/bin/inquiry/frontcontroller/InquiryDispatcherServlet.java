package bin.inquiry.frontcontroller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import bin.inquiry.control.Controller;
import bin.inquiry.controller.InquiryAnswerWrite;
import bin.inquiry.controller.InquiryAnswerWriteView;
import bin.inquiry.controller.InquiryDelete;
import bin.inquiry.controller.InquiryList;
import bin.inquiry.controller.InquirySelect;
import bin.inquiry.controller.InquiryUpdate;
import bin.inquiry.controller.InquiryUpdateView;
import bin.inquiry.controller.InquiryWrite;
import bin.inquiry.handler.InquiryHandlerAdapter;

public class InquiryDispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Log log = LogFactory.getLog(InquiryDispatcherServlet.class);
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String pathURL = requestURI.substring(contextPath.length());
		log.info("매핑명 조회 - " + pathURL);
		InquiryHandlerAdapter inquiryHandlerAdapter = null;
		Controller controller = null;
		if(pathURL.equals("/InquiryList.in")) {
			controller = new InquiryList();
			inquiryHandlerAdapter = controller.execute(request, response);
			log.info("전체 문의 글 확인 - " + inquiryHandlerAdapter);
		}
		else if(pathURL.equals("/InquirySelect.in")) {
			controller = new InquirySelect( );
			inquiryHandlerAdapter = controller.execute(request, response);
			log.info("상세 문의 글 확인 - " + inquiryHandlerAdapter);
		}
		else if(pathURL.equals("/InquiryWriteView.in")) {
			inquiryHandlerAdapter = new InquiryHandlerAdapter();
			inquiryHandlerAdapter.setPath("/WEB-INF/inquiry/inquiry_write.jsp");
			log.info("문의 글 등록 화면 뷰 확인 - " + inquiryHandlerAdapter);
		}
		else if(pathURL.equals("/InquiryWrite.in")) {
			controller = new InquiryWrite( );
			inquiryHandlerAdapter = controller.execute(request, response);
			log.info("문의 글 등록 확인 - " + inquiryHandlerAdapter);
		}
		else if(pathURL.equals("/InquiryUpdateView.in")) {
			controller = new InquiryUpdateView( );
			inquiryHandlerAdapter = controller.execute(request, response);
			log.info("문의 글 수정 화면 뷰 확인 - " + inquiryHandlerAdapter);
		}
		else if(pathURL.equals("/InquiryUpdate.in")) {
			controller = new InquiryUpdate( );
			inquiryHandlerAdapter = controller.execute(request, response);
			log.info("문의 글 수정 확인 - " + inquiryHandlerAdapter);
		}
		else if(pathURL.equals("/InquiryDeleteView.in")) {
			inquiryHandlerAdapter = new InquiryHandlerAdapter( );
			inquiryHandlerAdapter.setPath("/WEB-INF/inquiry/inquiry_delete.jsp");
			log.info("문의 글 삭제 화면 뷰 확인 - " + inquiryHandlerAdapter);
		}
		else if(pathURL.equals("/InquiryDelete.in")) {
			controller = new InquiryDelete();
			inquiryHandlerAdapter = controller.execute(request, response);
			log.info("문의 글 삭제 확인 - " + inquiryHandlerAdapter);
		}
		else if(pathURL.equals("/InquiryAnswerView.in")) {
			controller = new InquiryAnswerWrite( );
			inquiryHandlerAdapter = controller.execute(request, response);
			inquiryHandlerAdapter.setPath("/WEB-INF/inquiry/inquiry_answer_write.jsp");
			log.info("문의 답변 글 등록 화면 뷰 확인 - " + inquiryHandlerAdapter);
		}
		else if(pathURL.equals("/InquiryAnswer.in")) {
			controller = new InquiryAnswerWriteView();
			inquiryHandlerAdapter = controller.execute(request, response);
			log.info("문의 답변 글 등록 확인 - " + inquiryHandlerAdapter);
		}
		else if(pathURL.equals("/MainView.in")) {
			inquiryHandlerAdapter = new InquiryHandlerAdapter( );
			inquiryHandlerAdapter.setPath("/WEB-INF/mainview/mainview.jsp");
			log.info("메인 화면 이동 확인 - " + inquiryHandlerAdapter);
		}
		if(inquiryHandlerAdapter != null) {
			if(inquiryHandlerAdapter.isRedirect()) {
				response.sendRedirect(inquiryHandlerAdapter.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(inquiryHandlerAdapter.getPath());
				dispatcher.forward(request, response);
			}
		}
	}
}
