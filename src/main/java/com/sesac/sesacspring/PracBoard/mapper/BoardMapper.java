package com.sesac.sesacspring.PracBoard.mapper;

import com.sesac.sesacspring.PracBoard.domain.Board;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface BoardMapper {
    List<Board> getBoardAll(); // 전체 조회 (SELECT * FROM board;)

    void insertBoard(Board board); // 삽입 (insert)
    // mapper 작성 방법 2가지  1. sql 작성 2. xml 작성
    // 1) mapper 에는 메소드가 있고,  xml 에는 없는 경우 -> 실행했을 때 오류
    // 2) xml 에는 있고, mapper 에는 없는 경우 -> 실행 자체가 안됨!

    void patchBoard(Board board); // 수정 (update)
    void deleteBoard(int id); // 삭제 (delete)

    List<Board> searchBoard(String word); // 검색 (search)
}
