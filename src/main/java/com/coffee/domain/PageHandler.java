package com.coffee.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageHandler {
    private SearchCondition sc;

    private int totalCnt; // 총 게시물 갯수
    private int naviSize = 10; // 페이지 네비게이션의 크기
    private int totalPage; // 전체 페이지의 수
    private int beginPage;  // 내비게이션의 첫번째 페이지
    private int endPage;    // 내비게이션의 마지막 페이지
    private boolean showPrev;   // 이전 페이지로 이동하는 링크 보여줄것인지의 여부
    private boolean showNext;   // 다음 페이지로 이동하는 링크 보여줄것인지의 여부

    public PageHandler(int totalCnt, SearchCondition sc) {
        this.totalCnt = totalCnt;
        this.sc = sc;

        doPaging(totalCnt, sc);
    }

    public void doPaging(int totalCnt, SearchCondition sc) {
        this.totalCnt = totalCnt;

        totalPage = (int)Math.ceil(totalCnt / (double)sc.getPageSize()); // pageSize에 double 안붙여주면 255/10할 때 결과가 25.5가 아닌 25가 나와서 에러 생김
        beginPage = (sc.getPage()-1) / naviSize * naviSize + 1;
        endPage = Math.min(beginPage + naviSize - 1, totalPage); // beginPage+naviSize와 totalPage 중 작은값이 endPage가 되는것

        showPrev = beginPage != 1; // beginPage가 1이면 false여서 이전 페이지로 가는 버튼 안보여줌
        showNext = endPage != totalPage; // endPage가 totalPage면 다음 페이지로 가는 버튼 안보여줌
    }

    void print() {
        System.out.println("page = " + sc.getPage());
        System.out.print(showPrev ? "[이전]":"");
        for (int i = beginPage; i <= endPage; i++) {
            System.out.print(i + " ");
        }
        System.out.println(showNext ? "[다음]":" ");
    }
}


