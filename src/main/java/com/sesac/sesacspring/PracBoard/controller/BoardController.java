package com.sesac.sesacspring.PracBoard.controller;


import com.sesac.sesacspring.PracBoard.dto.BoardDTO;
import com.sesac.sesacspring.PracBoard.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/board/mybatis")
public class BoardController {

    // 동적 폼 전송 -> post, patch, delete 등 body에 데이터를 보내는 친구 = @RequestBody 객체로 받아야 한다.
    // 일반 폼 전송 (+ 동적 폼 전송 get) -> = 변수 하나하나 받을거 라면 : @RequestPram
    // = 객체로 받을거라면 : @ModelAttrbute (Setter 필수)

    // 5개의 method
    // 1. 전체 조회 -> board.html 랜더링
    // 2. 작성 (create) : axios (동적 폼 전송, post)
    // 3. 수정 (update) : axios (동적 폼 전송 , patch)
    // 4. 삭제 (delete) : axios (동적 폼 전송 ,delete)
    // 5. 검색 (조회) : axios (동적 폼 전송 , get)
    @Autowired
    BoardService boardService;

    // 1. 전체 조회
    @GetMapping("") // 경로 : /board/mybatis
    public String getBoard(Model model) {
        List<BoardDTO> result = boardService.getBoardAll();
        model.addAttribute("list", result);
        return "board";
    }

    @PostMapping("")
    @ResponseBody // 응답을 주기 위함!
    public boolean insertBoard(@RequestBody BoardDTO boardDTO) {
        // 2. 게시글 작성
        boardService.insertBoard(boardDTO);
        return true;
    }

    @PatchMapping("") // 경로 : /board/mybatis
    @ResponseBody // ResponseBody 를 안붙이면 템플릿 파일을 보여주는데 void 라면 현재의 템플릿파일을 그대로 다시 보여준다.
    public void patchBoard(@RequestBody BoardDTO boardDTO) {
        boardService.patchBoard(boardDTO);
    }

    @DeleteMapping("") // 경로 : /board/mybatis
    @ResponseBody // ResponseBody 를 안붙이면 템플릿 파일을 보여주는데 void 라면 현재의 템플릿파일을 그대로 다시 보여준다.
    public void deleteBoard(@RequestParam int id) {
        boardService.deleteBoard(id);
    }

    @GetMapping("/search") // 경로 : /board/mybatis/search
    @ResponseBody
    public int searchBoard(@RequestParam String word) {
        return boardService.searchBoard(word);
    }
}