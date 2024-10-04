package com.sparta.scheduler.page;


import com.sparta.scheduler.dto.response.SchedulerResponseDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Page {
    /* 현재 페이지 */
    private int pageNum;

    private int total;

    private int totalPages;

    private int startPage;

    private int endPage;

    private int pagingCount;


    public Page(int total, int pageNum, int size, int pagingCount) {
        this.total = total;
        this.pageNum = pageNum;
        this.pagingCount = pagingCount;

        if(total == 0){
            totalPages = 0;
            startPage = 0;
            endPage = 0;
        }
        else{
            totalPages = total / size;
            if(total % size > 0){
                totalPages++;
            }

            startPage = pageNum / pagingCount * pagingCount + 1;

            if(pageNum % pagingCount == 0){
                startPage -= pagingCount;
            }

            endPage = startPage + pagingCount - 1 ;

            if(endPage > totalPages){
                endPage = totalPages;
            }
        }
    }

}
