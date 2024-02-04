package com.sesac.sesacspring.PracBoard.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDTO {
    private int id;
    private String title;
    private String name;
    private String content;
    private String date;
}
