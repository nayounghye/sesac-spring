package com.sesac.sesacspring.PracBoard.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Board {
    private int id;
    private String title;
    private String name;
    private String content;
    private String date;
}

