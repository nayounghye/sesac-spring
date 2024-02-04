package com.sesac.sesacspring.PracBoard.service;


import com.sesac.sesacspring.PracBoard.domain.Board;
import com.sesac.sesacspring.PracBoard.dto.BoardDTO;
import com.sesac.sesacspring.PracBoard.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {
    // Mapper 호출
    @Autowired
    BoardMapper boardMapper;

    public List<BoardDTO> retrieveAll(){
        List<Board> board = BoardMapper.retrieveAll();
        List<BoardDTO> result = new ArrayList<>();

    }

    public void createBoard (String title, String name, String content, String date)
    {BoardMapper.createBoard(title,name,content,date);}


}
