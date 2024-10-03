package com.sparta.scheduler.page;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageMakeDTO {
    private Page page;
    private int totalCount;
    private int startPage;
    private int endPage;
    private boolean prev;
    private boolean next;
    private int displayPageNum = 9;

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        calcData();
    }

    private void calcData() {
        endPage = (int) (Math.ceil(page.getPageNum()/(double)displayPageNum)*displayPageNum);

        startPage = (endPage - displayPageNum) + 1;
        if(startPage <= 0) startPage = 1;

        int tempEndPage = (int) (Math.ceil(totalCount/(double)page.getAmount()));
        if(endPage > tempEndPage){
            endPage = tempEndPage;
        }

        prev = startPage == 1 ? false : true;
        next = endPage * page.getAmount() < totalCount ? true : false;
    }


}
