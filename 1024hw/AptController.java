package com.mycom.myapp.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.mycom.myapp.dto.AptDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class AptController {
    //매물 등록
    @PostMapping("/board")
    public int insert(AptDto aptDto) {
        System.out.println("POST/insert");
    	return 1;
    }
    //list
    @GetMapping("/board")
    public String boardList(@RequestParam Map<String,String>map,Model model) {
    	System.out.println("GET/list");
        List<AptDto>list=new ArrayList<>();
        list.add(new AptDto());
    	return "list";
    }
    
    
    //상세
    @GetMapping("/board/{aptNo}")
    public AptDto detail(@PathVariable int aptNo) {
        System.out.println("/GET/Detail");
    	AptDto dto=new AptDto();
    	return dto;
    }
    
    //검색 
    @PostMapping("/board/{searchWord}")
    public List<AptDto> Search(@PathVariable String searchWord) {
        List<AptDto>list=new ArrayList<AptDto>();
    	System.out.println("/Get/search");
        return list;
    }
    
    @GetMapping("/board/main")
    public String boardmain() {
    	return "boardmain";
    }
}