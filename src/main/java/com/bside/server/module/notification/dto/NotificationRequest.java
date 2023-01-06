package com.bside.server.module.notification.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class NotificationRequest {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String token;

    @ApiModelProperty(example = "true")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private boolean isNotification;
}
