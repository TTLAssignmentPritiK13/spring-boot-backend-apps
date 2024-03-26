package com.blogapp.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/*
 * TODO : Add more paging configuration values
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PagingResponse {

    private int pageNumber;
    private int pageSize;
    private int totalElements;
    private int totalPages;
    private int firstPage;
    private int lastPage;
    private int currentPage;

    public PagingResponse(int pageNumber, int pageSize) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        pagingOperation(pageNumber,pageSize);
    }

    public Pageable pagingOperation(int pageNumber, int pageSize){
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        return pageable;

    }
}
