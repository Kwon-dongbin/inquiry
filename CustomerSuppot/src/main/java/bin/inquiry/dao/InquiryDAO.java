package bin.inquiry.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import bin.inquiry.dto.InquiryDTO;
import bin.inquiry.service.InquiryService;

public class InquiryDAO implements InquiryService {
	
	private static final Log log = LogFactory.getLog(InquiryDAO.class);

	@Override
	public ArrayList<InquiryDTO> InquiryList() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<InquiryDTO> arrayList = new ArrayList<InquiryDTO>();
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection();
			String sql = "select inquiry_num, inquiry_title, user_id ,inquiry_date, inquiry_status from customer_suppot order by inquiry_date desc";
			log.info("DAO의 SQL 확인 - " + sql);
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				InquiryDTO inquiryDTO = new InquiryDTO();
				inquiryDTO.setInquiry_num(resultSet.getInt("inquiry_num"));
				inquiryDTO.setInquiry_title(resultSet.getString("inquiry_title"));
				inquiryDTO.setUser_id(resultSet.getString("user_id"));
				inquiryDTO.setInquiry_date(resultSet.getString("inquiry_date"));
				inquiryDTO.setInquiry_status(resultSet.getInt("inquiry_status"));
				arrayList.add(inquiryDTO);	
				log.info("DAO에서 arrayList" + arrayList);
			}
			if(arrayList.isEmpty()) {
				log.info("등록한 글이 없습니다. 글을 등록해 주세요");
			}
		} catch (Exception e) {
			log.info("전체 글 조회 실패 - " + e);
		} finally {
			try {
				if(resultSet != null)
	                resultSet.close();
	            if(preparedStatement != null)
	                preparedStatement.close();
	            if(connection != null)
	                connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return arrayList;
	}

	@Override
	public InquiryDTO InquirySelect(int inquiry_num) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		InquiryDTO inquiryDTO = new InquiryDTO();
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection();
			String sql = "select inquiry_num, inquiry_title, inquiry_content, inquiry_answer from customer_suppot ";
			sql += "where inquiry_num = ? ";
			log.info("DAO의 SQL 확인 - " + sql);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, inquiry_num);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				inquiryDTO.setInquiry_num(resultSet.getInt("inquiry_num"));
				inquiryDTO.setInquiry_title(resultSet.getString("inquiry_title"));
				inquiryDTO.setInquiry_content(resultSet.getString("inquiry_content"));
				inquiryDTO.setInquiry_answer(resultSet.getString("inquiry_answer"));
			}
		} catch (Exception e) {
			log.info(inquiry_num+"번 글 조회 실패 - " + e);
		} finally {
			try {
				if(resultSet != null)
	                resultSet.close();
	            if(preparedStatement != null)
	                preparedStatement.close();
	            if(connection != null)
	                connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return inquiryDTO;
	}

	@Override
	public InquiryDTO InquiryWrite(InquiryDTO inquiryDTO) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection();
			String sql = "insert into customer_suppot (inquiry_num, inquiry_title, inquiry_content, user_id)";
			sql += "values (inquiry_numplus.nextval , ? , ? , ? )";
			log.info("DAO의 SQL 확인 - " + sql);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, inquiryDTO.getInquiry_title());
			preparedStatement.setString(2, inquiryDTO.getInquiry_content());
			preparedStatement.setString(3, inquiryDTO.getUser_id());
			int count = preparedStatement.executeUpdate();
			if (count > 0) {
				connection.setAutoCommit(false);
				connection.commit();
				log.info("커밋되었습니다.");
			} else {
				connection.rollback();
				log.info("롤백되었습니다.");
			}
		} catch (Exception e) {
			log.info("글 쓰기 실패 - " + e);
		} finally {
			try {
				if(preparedStatement != null)
	                preparedStatement.close();
	            if(connection != null)
	                connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return inquiryDTO;
	}

	@Override
	public InquiryDTO InquiryUpdate(InquiryDTO inquiryDTO) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection();
			String sql = "update customer_suppot set inquiry_title = ?, inquiry_content = ? ";
			sql += "where inquiry_num = ?";
			log.info("DAO의 SQL 확인 - " + sql);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, inquiryDTO.getInquiry_title());
			preparedStatement.setString(2, inquiryDTO.getInquiry_content());
			preparedStatement.setInt(3, inquiryDTO.getInquiry_num());
			int count = preparedStatement.executeUpdate();
			if (count > 0) {
				connection.setAutoCommit(false);
				connection.commit();
				log.info("커밋되었습니다.");
			} else {
				connection.rollback();
				log.info("롤백되었습니다.");
			}
		} catch (Exception e) {
			log.info("문의 글 수정 실패 - " + e);
		} finally {
			try {
				if(preparedStatement != null)
	                preparedStatement.close();
	            if(connection != null)
	                connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return inquiryDTO;
	}

	@Override
	public InquiryDTO InquiryDelete(int inquiry_num) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection();
			String sql = "delete from customer_suppot ";
			sql += "where inquiry_num = ?";
			log.info("DAO의 SQL 확인 - " + sql);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, inquiry_num);
			int count = preparedStatement.executeUpdate();
			log.info("DB에서 삭제할 번호의 DAO확인 : " + inquiry_num);
			if (count > 0) {
				connection.setAutoCommit(false);
				connection.commit();
				log.info("커밋되었습니다.");
			} else {
				connection.rollback();
				log.info("롤백되었습니다.");
			}
		} catch (Exception e) {
			log.info("부서 삭제 실패 - " + e);
		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public InquiryDTO InquiryAnswerWrite(InquiryDTO inquiryDTO) {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    try {
	        Context context = new InitialContext();
	        DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
	        connection = dataSource.getConnection();
	        String sql = "UPDATE customer_suppot SET inquiry_answer = ?, inquiry_status = CASE WHEN ? IS NULL OR ? = '' THEN 0 ELSE 1 END WHERE inquiry_num = ?";
	        log.info("DAO의 SQL 확인 - " + sql);
	        preparedStatement = connection.prepareStatement(sql);
	        preparedStatement.setString(1, inquiryDTO.getInquiry_answer());
	        preparedStatement.setString(2, inquiryDTO.getInquiry_answer());
	        preparedStatement.setString(3, inquiryDTO.getInquiry_answer());
	        preparedStatement.setInt(4, inquiryDTO.getInquiry_num());
	        int count = preparedStatement.executeUpdate();
	        if (count > 0) {
	            connection.setAutoCommit(false);
	            connection.commit();
	            log.info("커밋되었습니다.");
	        } else {
	            connection.rollback();
	            log.info("롤백되었습니다.");
	        }
	    } catch (Exception e) {
	        log.info("답변 글 쓰기 실패 - " + e);
	    } finally {
	        try {
	            if (preparedStatement != null)
	                preparedStatement.close();
	            if (connection != null)
	                connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return inquiryDTO;
	}
}
