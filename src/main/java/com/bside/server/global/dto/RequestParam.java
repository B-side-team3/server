package com.bside.server.global.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;

@NoArgsConstructor
@Setter
@Getter
public class RequestParam {
    private Integer size = 50;
    private Integer page = 0;
    private PageRequest pageRequest;

    public PageRequest getPageRequest(){
        return PageRequest.of(this.page, this.size);
    }
}
