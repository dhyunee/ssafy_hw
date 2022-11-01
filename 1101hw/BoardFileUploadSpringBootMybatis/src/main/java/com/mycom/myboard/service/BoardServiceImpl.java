package com.mycom.myboard.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycom.myboard.dao.BoardDao;
import com.mycom.myboard.dto.BoardDto;
import com.mycom.myboard.dto.BoardFileDto;
import com.mycom.myboard.dto.BoardParamDto;
import com.mycom.myboard.dto.BoardResultDto;

// Controller의 요청에 의해 필요한 파라미터는 받고, 원하는 결과 데이터를 만들어서 리턴해 준다.
// Business Logic의 핵심은 바로 Service Layer에서!!
@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	BoardDao boardDao;
	
	private final int SUCCESS = 1;
	private final int FAIL = -1;
	
	// @Value <- application.properties 설정값으로 대체
	@Value("${app.fileupload.uploadPath}")
	String uploadPath;
	
	@Value("${app.fileupload.uploadDir}")
	String uploadFolder;
	
	@Override
	public BoardResultDto boardList(BoardParamDto boardParamDto) {
		BoardResultDto boardResultDto = new BoardResultDto();
		try {
			// 목록, 총 건수 가져오기
			List<BoardDto> list = boardDao.boardList(boardParamDto);
			int count = boardDao.boardListTotalCount();
			boardResultDto.setList(list);
			boardResultDto.setCount(count);
			boardResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			boardResultDto.setResult(FAIL);
		}
		return boardResultDto;
	}

	@Override
	public BoardResultDto boardListSearchWord(BoardParamDto boardParamDto) {
		BoardResultDto boardResultDto = new BoardResultDto();
		
		try {
			// 목록, 총 건수 가져오기
			List<BoardDto> list = boardDao.boardListSearchWord(boardParamDto);
			int count = boardDao.boardListSearchWordTotalCount(boardParamDto);
			boardResultDto.setList(list);
			boardResultDto.setCount(count);
			boardResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			boardResultDto.setResult(FAIL);
		}
		return boardResultDto;
	}

	@Override
	public BoardResultDto boardDetail(BoardParamDto boardParamDto) {
		BoardResultDto boardResultDto = new BoardResultDto();
		try {
			
			// 현재 게시글을 현재 사용자가 읽었는지(visit) 확인
			int userReadCount = boardDao.boardUserReadCount(boardParamDto);

			// 안읽었으면 신규로 게시글 읽었다는 insert하고, 게시글 조회수 +1
			if (userReadCount == 0) {
				boardDao.boardUserReadInsert(boardParamDto.getBoardId(), boardParamDto.getUserSeq());
				boardDao.boardReadCountUpdate(boardParamDto.getBoardId());
			}
			// 읽었으면 무시

			// DB에서 게시글 정보를 가져온다.
			BoardDto boardDto = boardDao.boardDetail(boardParamDto);
			
			// 게시글 작성자와 상세 조회하는 사용자의 동일인 여부 확인
			if (boardDto.getUserSeq() == boardParamDto.getUserSeq()) {
				boardDto.setSameUser(true);
			} else {
				boardDto.setSameUser(false);
			}
			
			// 해당 게시글에 대한 첨부파일 정보를 추가
			List<BoardFileDto> fileList = boardDao.boardDetailFileList(boardDto.getBoardId());
			boardDto.setFileList(fileList);
			
			// boardResultDto의 일부인  boardDto를 선정
			boardResultDto.setDto(boardDto);
			
			boardResultDto.setResult(SUCCESS);
			
		} catch (Exception e) {
			e.printStackTrace();
			boardResultDto.setResult(FAIL);
		}
		return boardResultDto;
	}

	@Override
	public BoardResultDto boardUpdate(BoardDto dto, MultipartHttpServletRequest request) {
		BoardResultDto boardResultDto = new BoardResultDto();
		try {
			boardDao.boardUpdate(dto); // 게시글 수정
			
			File uploadDir = new File(uploadPath + File.separatorChar + uploadFolder); // 업로드된 파일이 저장될 폴더(디렉토리)
		    if (!uploadDir.exists()) uploadDir.mkdir(); // 없으면 새로 생성
		    
		    // 기존에 첨부된 물리적인 파일 삭제, 첨부파일이 여러개 감안
		    List<String> fileUrlList = boardDao.boardFileUrlDeleteList(dto.getBoardId());
		    
		    for (String fileUrl : fileUrlList) {
		    	// fileUrl은 upload/... 형태
				File file = new File(uploadPath + File.pathSeparator + fileUrl);
				if (file.exists()) {
					file.delete();
				}
			}
		    
		    // 테이블에서 게시판 파일 삭제
		    boardDao.boardFileDelete(dto.getBoardId());
		    
		    // 수정과정에 새로 추가된 첨부 파일 등록
			List<MultipartFile> fileList = request.getFiles("file");
	        
	        for (MultipartFile partFile : fileList) {
				int boardId = dto.getBoardId(); // 직전 등록된 게시글의 key값
				String fileName = partFile.getOriginalFilename(); // 첨부된 원래 파일 명, 이 이름으로는 바로 저장하지 않고 UUID를 이용해서 중복불가한 파일 이름을 만든다.
				
				// Random UUID File id
				UUID uuid = UUID.randomUUID(); // 대체될 파일 이름
				
				// 파일 확장자
				String extension = FilenameUtils.getExtension(fileName); // 원래 파일 확장자만 추출
				
				// 실제 저장할 파일 전체 이름은
				String savingFileName = uuid + "." + extension;
				
				File destFile = new File(uploadPath + File.separatorChar + uploadFolder + File.separator + savingFileName);
				
				// 파일 객체를 통해서 파일을 저장
				partFile.transferTo(destFile);
				
				// 테이블에 첨부파일 정보 저장
				BoardFileDto boardFileDto = new BoardFileDto();
				boardFileDto.setBoardId(boardId);
				boardFileDto.setFileName(fileName);
				boardFileDto.setFileSize(partFile.getSize());
				boardFileDto.setFileContentType(partFile.getContentType());
				boardFileDto.setFileUrl(uploadFolder + "/" + savingFileName);
				
				boardDao.boardFileInsert(boardFileDto);
				
	        }
	        boardResultDto.setResult(SUCCESS);
			
		} catch (Exception e) {
			e.printStackTrace();
			boardResultDto.setResult(FAIL);
		}
		return boardResultDto;
	}

	@Override
	public BoardResultDto boardDelete(int boardId) {
		BoardResultDto boardResultDto = new BoardResultDto();
		try {
			List<String> fileUrlList = boardDao.boardFileUrlDeleteList(boardId);
		    
		    for (String fileUrl : fileUrlList) {
		    	// fileUrl은 upload/... 형태
				File file = new File(uploadPath + File.pathSeparator + fileUrl);
				if (file.exists()) {
					file.delete();
				}
			}
		    
		    // 삭제 순서
		    boardDao.boardReadCountDelete(boardId); // 조회수 관련 삭제
		    boardDao.boardFileDelete(boardId); // 첨부 파일 삭제 
			boardDao.boardDelete(boardId); // 마지막으로 게시판 삭제
			
			boardResultDto.setResult(SUCCESS);
			
		} catch (Exception e) {
			e.printStackTrace();
			boardResultDto.setResult(FAIL);
		}
		return boardResultDto;
	}
	
	@Override
	// DB transaction 정책 // 파일 저장 복구 원복 X
	@Transactional // 게시글 등록 중 오류 발생 시 전체 내용이 DB에 들어가지 않도록 하기 위해
	public BoardResultDto boardInsert(BoardDto boardDto, MultipartHttpServletRequest request) {
	    BoardResultDto boardResultDto = new BoardResultDto();
	    
	    // 게시글을 테이블에 등록
	    // 물리적인 파일 저장
	    // 여러 개의 파일이 기본
	    // Multipart의 각 파트별로 파일을 구분해서 물리적으로 저장 => 해당 파일에 대한 업로드 정보를 테이블에 저장
	    try {
	        boardDao.boardInsert(boardDto); // insert 되는 건수
	        System.out.println("gemerated key : " + boardDto.getBoardId());
	        
	        File uploadDir = new File(uploadPath + File.separatorChar + uploadFolder); // 업로드된 파일이 저장될 폴더(디렉토리)
	        if (!uploadDir.exists()) uploadDir.mkdir(); // 없으면 새로 생성
	        
	        List<MultipartFile> fileList = request.getFiles("file");
	        
//	        // NullPoinrer 예외 발생
//	        String str = null;
//	        str.length();
	        
	        for (MultipartFile partFile : fileList) {
				int boardId = boardDto.getBoardId(); // 직전 등록된 게시글의 key값
				String fileName = partFile.getOriginalFilename(); // 첨부된 원래 파일 명, 이 이름으로는 바로 저장하지 않고 UUID를 이용해서 중복불가한 파일 이름을 만든다.
				
				// Random UUID File id
				UUID uuid = UUID.randomUUID(); // 대체될 파일 이름
				
				// 파일 확장자
				String extension = FilenameUtils.getExtension(fileName); // 원래 파일 확장자만 추출
				
				// 실제 저장할 파일 전체 이름은
				String savingFileName = uuid + "." + extension;
				
				File destFile = new File(uploadPath + File.separatorChar + uploadFolder + File.separator + savingFileName);
				
				// 파일 객체를 통해서 파일을 저장
				partFile.transferTo(destFile);
				
				// 테이블에 첨부파일 정보 저장
				BoardFileDto boardFileDto = new BoardFileDto();
				boardFileDto.setBoardId(boardId);
				boardFileDto.setFileName(fileName);
				boardFileDto.setFileSize(partFile.getSize());
				boardFileDto.setFileContentType(partFile.getContentType());
				boardFileDto.setFileUrl(uploadFolder + "/" + savingFileName);
				
				boardDao.boardFileInsert(boardFileDto);
			
	        }
	        boardResultDto.setResult(SUCCESS);

	    }catch(IOException e) { // 혹은 Exception -> IOException으로 설정
	    	// 만약 파일 저장 작업 중 오류가 발생하면 IOException catch block의 코드가 실행됨
	    	// 2가지 작업 필요
	    	// #1. 예외 발생 이전에 저장된 파일 모두 삭제 => 짓접 물리적인 파일 섹제 직압 점검 수행;
	    	// #2. 이전에 테이블에 등록된 작업 취수 => Spring의 @transaction을 이용하기 위해 RuntimeException 계열의 예외를 발생 throw new RunTimeException()
	        e.printStackTrace();
	        boardResultDto.setResult(FAIL);
	        
//	        // NullPointer 관련 RuntimeException 발생 시 Transaction에 걸리게
//	        throw new RuntimeException();
	    }
	    
	    return boardResultDto;
	}
}
