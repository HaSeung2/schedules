package com.sparta.scheduler.page;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Page {
    /* 현재 페이지 */
    private int pageNum;

    /* 한 페이지 당 보여질 게시물 갯수 */
    private int amount;
    
    public Page(){
        this.pageNum = 1;
        this.amount = 10;
    }
    
    // 특정 페이지의 게시글 시작 번호, 게시글 시작 행 번호
    public int getPageStart(){
        return (this.pageNum - 1) * amount;
    }
    
    public void setPageNum(int pageNum){
        if(pageNum <= 0){
            this.pageNum = 1;
        }
        else{
            this.pageNum = pageNum;
        }
    }
    
    public void setAmount(int pageCount){
        int cnt = this.amount;
        if(pageCount != cnt){
            this.amount = cnt;
        }
        else{
            this.amount = pageCount;
        }
    }
}
