package com.bside.server.global.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class PageResponse<T> {
    private List<T> contents;
    private long count;
    private long page;
    private long size;
    private long total;

    @Builder
    public PageResponse(List<T> contents, long count, long page, long size, long total) {
        this.contents = contents;
        this.count = count;
        this.page = page;
        this.size = size;
        this.total = total;
    }

    public PageResponse(Page<T> page) {
        this.contents = page.getContent();
        this.count = page.getNumberOfElements();
        this.page = page.getPageable().getPageNumber();
        this.size = page.getPageable().getPageSize();
        this.total = page.getTotalElements();
    }
}
