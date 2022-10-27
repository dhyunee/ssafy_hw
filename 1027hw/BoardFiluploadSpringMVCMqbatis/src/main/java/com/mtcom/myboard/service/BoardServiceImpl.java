package com.mtcom.myboard.service;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mtcom.myboard.dao.BoardDao;
import com.mtcom.myboard.dto.BoardDto;
import com.mtcom.myboard.dto.BoardFileDto;
import com.mtcom.myboard.dto.BoardParamDto;
import com.mtcom.myboard.dto.BoardResultDto;

//Controller의 요청에 의해 필요한 파라미터는 받고, 원하는 결과 데이터를 만들어서 리턴해준다.
//Business logic의 핵심을 바로 Service layer에서!!!
@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardDao dao;

	private final int SUCCESS = 1;
	private final int FAIL = -11;
//C:\Users\SSAFY\Documents\workspace-sts-3.9.14.RELEASE\BoardFiluploadSpringMVCMqbatis\src\main\webapp\resources\static
//	private final String uploadPath="C:"+File.separator+"Users"+File.separator+"SSAFY"+File.separator+"Documents"+
//			File.separator+"workspace-sts-3.9.14.RELEASE"+File.separator+"BoardFiluploadSpringMVCMqbatis"+File.separator+"src"+
//			File.separator+"main"+File.separator+"webapp"+File.separator+"resources"+File.separator+"static";
//	
//	C:\Users\rlthd\Desktop\SSAFY\spring\BoardFiluploadSpringMVCMqbatis\src\main\webapp\resources\static
	private final String uploadPath="C:"+File.separator+"Users"+File.separator+"rlthd"+File.separator+"SSAFY"+
			File.separator+"SSAFY"+File.separator+"BoardFiluploadSpringMVCMqbatis"+File.separator+"src"+
			File.separator+"main"+File.separator+"webapp"+File.separator+"resources"+File.separator+"static";
	
	private final String uploadFolder="upload"; 

	@Override
	public BoardResultDto boardList(BoardParamDto boardParamDto) {
		BoardResultDto boardResultDto = new BoardResultDto();

		try {
			// 목록, 총건수를 가져온다.
			List<BoardDto> list = dao.boardList(boardParamDto);
			int count = dao.boardListTotalCount();
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
			// 목록, 총건수를 가져온다.
			List<BoardDto> list = dao.boardListSearchWord(boardParamDto);
			int count = dao.boardListSearchWordTotalCount(boardParamDto);
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
			// db에서 게시글 정보를 가져온다.
			BoardDto boardDto = dao.boardDetail(boardParamDto);
			// 게시글 작성자와 현재 상세조회하고 있는 사용자의 동일인 여부 확인 필요
			if (boardDto.getUserSeq() == boardParamDto.getUserSeq()) {
				boardDto.setSameUser(true);
			} else
				boardDto.setSameUser(false);

			// boardResultDto의 일부인 boardDto를 설정
			boardResultDto.setDto(boardDto);
			boardResultDto.setResult(SUCCESS);

		} catch (Exception e) {
			e.printStackTrace();
			boardResultDto.setResult(FAIL);
		}

		return boardResultDto;
	}

	@Override
	public BoardResultDto boardUpdate(BoardDto boardDto) {
		BoardResultDto boardResultDto = new BoardResultDto();

		try {
			int ret = dao.boardUpdate(boardDto);
			if(ret==1) {//update 되는 건수
				boardResultDto.setResult(SUCCESS);
			}else boardResultDto.setResult(FAIL);

		} catch (Exception e) {
			e.printStackTrace();
			boardResultDto.setResult(FAIL);
		}
		return boardResultDto;
	}

//	@Override
//	public BoardResultDto boardDelete(int boardId) {
//		BoardResultDto boardResultDto = new BoardResultDto();
//
//		try {
//			int ret = dao.boardDelete(boardId);
//			if(ret==1) {//delete 되는 건수
//				boardResultDto.setResult(SUCCESS);
//			}else boardResultDto.setResult(FAIL);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			boardResultDto.setResult(FAIL);
//		}
//		return boardResultDto;
//	}
	
	
	@Override
	public BoardResultDto boardDelete(int boardId) {
	    
	    BoardResultDto boardResultDto = new BoardResultDto();
	    
	    try {
	        List<String> fileUrlList = dao.boardFileUrlDeleteList(boardId);
	        for(String fileUrl : fileUrlList) {
	            File file = new File(uploadPath + File.separator, fileUrl);                
	            if(file.exists()) {
	                file.delete();
	            }
	        }
	        
	        // 삭제 순서
	        dao.boardFileDelete(boardId);
	        dao.boardDelete(boardId);        
	        boardResultDto.setResult(SUCCESS);
	        
	    }catch(Exception e) {
	        e.printStackTrace();
	        boardResultDto.setResult(FAIL);
	        // 파일업로드 등이 실패할 경우 IOException 이 발생하는데, IOException 을 throw 할 경우 Rollback 되지 않는다.
	        // UncheckedException 발생
	        throw new RuntimeException();            
	    }
	    
	    return boardResultDto;
	}
	
	

	@Override
	public BoardResultDto boardInsert(BoardDto boardDto,MultipartHttpServletRequest request) {
	    BoardResultDto boardResultDto = new BoardResultDto();
	  //게시글을 테이블에 등록
    	//물리적인 파일 저장
    	//여러 개의 파일이 기본
    	//multipart의 각 part 별로 파일을 구분해서 물리적으로 저장=> 파일 업로드 정보를 테이블에 저장
	    try {
	    	dao.boardInsert(boardDto); 
	    	System.out.println("generated key:" +boardDto.getBoardId());
	    
	    	File uploadDir=new File(uploadPath+File.separatorChar+uploadFolder);//업로드된 파일이 저장될 폴더(디렉토리)
	    	if(!uploadDir.exists())uploadDir.mkdir();
	    	
	    	List<MultipartFile>fileList=request.getFiles("file");
	    	
	    	for(MultipartFile partFile:fileList) {
	    		int boardId=boardDto.getBoardId();
	    		String fileName=partFile.getOriginalFilename();//첨부된 원래 파일 명,이 이름으로는 바로 저장하지 않고 UUID를 이용해서 중복 불가한 파일이름을 만든다.
	    		
	    		//Random UUID File id
	    		 UUID uuid=UUID.randomUUID();//대체될 파일 이름
	    		 
	    		 //파일 확장자
	    		 String extension=FilenameUtils.getExtension(fileName);//원래 파일의 확장자만 추출
	    		 
	    		 //실제 저장할 파일 전체 이름은 
	    		 String savingFileName=uuid+"."+extension;
	    		 
	    		 File destFile=new File(uploadPath+File.separatorChar+uploadFolder+File.separator+savingFileName);
	    	
	    		 //파일 객체를 통해서 파일을 저장
	    		 partFile.transferTo(destFile);
	    		 
	    		 //테이블에 첨부파일 정보 저장
	    		 BoardFileDto boardFileDto =new BoardFileDto();
	    		 boardFileDto.setboardId(boardId);
	    		 boardFileDto.setFileName(fileName);
	    		 boardFileDto.setFileSize(partFile.getSize());
	    		 boardFileDto.setFileContentType(partFile.getContentType());
	    		 boardFileDto.setFileUrl(uploadFolder+"/"+savingFileName);
	    		 
	    		 dao.boardFileInsert(boardFileDto);
	    		 
	    	}
	    	
	    	boardResultDto.setResult(SUCCESS);
	    	//물리적인 파일 업로드 경로 설정
	    	
//	    	int ret = dao.boardInsert(boardDto); // insert 되는 건수   
//	        if( ret == 1 ) {
//	            boardResultDto.setResult(SUCCESS);
//	        }else {
//	            boardResultDto.setResult(FAIL);
//	        }

	    }catch(Exception e) {
	        e.printStackTrace();
	        boardResultDto.setResult(FAIL);
	    }
	    
	    return boardResultDto;
	}

}
