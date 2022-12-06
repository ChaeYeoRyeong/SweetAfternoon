package com.coffee.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.util.UriComponentsBuilder;

@Getter
@Setter
public class SearchCondition {
    private Integer page = 1;
    private Integer pageSize = 10;
    private String keyword = "";
    private Integer offset = 0;
    private String option = "";

    public SearchCondition() {}

    public SearchCondition(Integer page, Integer pageSize, String keyword, String option) {
        this.page = page;
        this.pageSize = pageSize;
        this.keyword = keyword;
        this.option = option;
    }

    public String getQueryString(Integer page) { // 페이지를 주고 해당 페이지에 대한 네비게이션도 해줘야 함
        return UriComponentsBuilder.newInstance()
                .queryParam("page", page) // 지정된 페이지로 페이지를 셋팅(페이지를 지정해주면 이 페이지를 쓰면 됨)
                .queryParam("pageSize", pageSize)
                .queryParam("option", option)
                .queryParam("keyword", keyword)
                .build().toString();
    }

    public String getQueryString() { // 검색 결과 내용을 받다가 목록으로 돌아올 때 SearchCondition의 iv가 값을 유지하게 해주는 메소드
        // ?page=1&pageSize=10&option=T&keyword="title" 이런 식으로 줘야하는데 이걸 일일히 만들기 불편하니까 getQueryString 사용
        return getQueryString(page); // 페이지를 지정해주지 않으면 sc의 getPage()를 갖다쓰게 함
    }

    public Integer getOffset() {
        return (page-1)*pageSize ;
    }

    @Override
    public String toString() {
        return "SearchCondition{" +
                "page=" + page +
                ", pageSize=" + pageSize +
                ", offset=" + getOffset() +
                ", option='" + option + '\'' +
                ", keyword='" + keyword + '\'' +
                '}';
    }
}
