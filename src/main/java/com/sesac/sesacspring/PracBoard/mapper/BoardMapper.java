package com.sesac.sesacspring.PracBoard.mapper;

import com.sesac.sesacspring.PracBoard.domain.Board;
import com.sesac.sesacspring.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BoardMapper {
    @Select("SELECT * FROM board")
    List<Board> getAllBoards();

    @Select("SELECT * FROM board WHERE title LIKE #{word}")
    List<Board> searchBoardsByTitle(String word);

    @Insert("insert into board(title, name, content, date) values(#{title}, #{name}, #{content} #{date})")
    void createBoard(
            String title, String name, String content, String date
    );

    @Update("UPDATE board SET nickname=#{nickname} WHERE id=#{id}")
    void editBoard(
            int id, String nickname, String title, String content
    );

    @Delete("DELETE FROM board WHERE id=#{id}")
    void deleteBoard(
            int id
    );
}
