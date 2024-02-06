package com.sesac.sesacspring.mybatis.PracBoard.service;


import com.sesac.sesacspring.mybatis.PracBoard.domain.Board;
import com.sesac.sesacspring.mybatis.PracBoard.dto.BoardDTO;
import com.sesac.sesacspring.mybatis.PracBoard.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {
    // Mapper 호출
    @Autowired
    BoardMapper boardMapper;

    public List<BoardDTO> getBoardAll() {
        List<Board> result = boardMapper.getBoardAll();
        List<BoardDTO> boards = new ArrayList<>();

        for (Board board : result) {
            BoardDTO boardDTO = new BoardDTO();
            boardDTO.setBoardID(board.getId());
            boardDTO.setTitle(board.getTitle());
            boardDTO.setWriter(board.getWriter());
            boardDTO.setRegistered(board.getRegistered());
            boardDTO.setNo(100 + board.getId());
            boards.add(boardDTO);
        }
        return boards;
    }

    public boolean insertBoard(BoardDTO boardDTO) {
        Board board = new Board();
        board.setTitle(boardDTO.getTitle());
        board.setContent(boardDTO.getContent());
        board.setWriter(boardDTO.getWriter());

        boardMapper.insertBoard(board);
        return true;
    }

    public void patchBoard(BoardDTO boardDTO) {
        // board.getBoardID // title, content, writer
        Board board = new Board();
        board.setId(boardDTO.getBoardID()); // update 의 where 에 들어갈 친구
        board.setTitle(boardDTO.getTitle());
        board.setContent(boardDTO.getContent());
        board.setWriter(boardDTO.getWriter());
        boardMapper.patchBoard(board);

    }

    public void deleteBoard(int id) {
        boardMapper.deleteBoard(id);
    }

    public int searchBoard(String word){
        List<Board> result = boardMapper.searchBoard(word);
        return result.size();
    }
}
