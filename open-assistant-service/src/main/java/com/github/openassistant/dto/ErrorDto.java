package com.github.openassistant.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@ApiModel
@Builder
public class ErrorDto {

    @ApiModelProperty(notes = "Error code")
    private int code;

    @ApiModelProperty(notes = "Error type")
    private String title;

    @ApiModelProperty(notes = "Error message")
    private String message;
}
