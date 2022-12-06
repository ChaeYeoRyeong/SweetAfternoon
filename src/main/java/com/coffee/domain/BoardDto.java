package com.coffee.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.*;

@Getter
@Setter
@ToString
public class BoardDto {
    private Integer board_no;
    private String  board_title;
    private String  board_writer;
    private String  board_content;
    private Date    board_reg_date;
    private Date    board_up_date;
    private int     board_view_cnt;
    private int     board_comment_cnt;
    private int     adminCheck;

    public BoardDto() {}

    public BoardDto(String board_title, String board_content, String board_writer){
        this.board_title = board_title;
        this.board_content = board_content;
        this.board_writer = board_writer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardDto boardDto = (BoardDto) o;
        return Objects.equals(board_no, boardDto.board_no) && Objects.equals(board_title, boardDto.board_title) && Objects.equals(board_writer, boardDto.board_writer) && Objects.equals(board_content, boardDto.board_content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(board_no, board_title, board_writer, board_content);
    }
}