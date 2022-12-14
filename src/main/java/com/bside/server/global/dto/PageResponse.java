package com.bside.server.global.dto;

import lombok.Builder;
import org.springframework.data.domain.Page;

import java.util.List;

public class PageResponse<T> {
    private List<T> value;
    private long count;
    private long offset;
    private long limit;
    private long total;

    @Builder
    public PageResponse(List<T> value, long count, long offset, long limit, long total) {
        this.value = value;
        this.count = count;
        this.offset = offset;
        this.limit = limit;
        this.total = total;
    }

    public PageResponse(Page<T> page) {
        this.value = page.getContent();
        this.count = page.getNumberOfElements();
        this.offset = page.getPageable().getPageNumber();
        this.limit = page.getPageable().getPageSize();
        this.total = page.getTotalElements();
    }
}
