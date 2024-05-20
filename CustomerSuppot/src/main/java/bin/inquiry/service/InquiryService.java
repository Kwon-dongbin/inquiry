package bin.inquiry.service;

import java.util.ArrayList;

import bin.inquiry.dto.InquiryDTO;

public interface InquiryService {
	public ArrayList<InquiryDTO> InquiryList();
	
	public InquiryDTO InquirySelect(int inquiry_num);
	
	public InquiryDTO InquiryWrite(InquiryDTO inquiryDTO);
	
	public InquiryDTO InquiryUpdate(InquiryDTO inquiryDTO);
	
	public InquiryDTO InquiryDelete(int inquiry_num);
	
	public InquiryDTO InquiryAnswerWrite(InquiryDTO inquiryDTO);
}
