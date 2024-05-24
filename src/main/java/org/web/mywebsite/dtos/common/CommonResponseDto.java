package org.web.mywebsite.dtos.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.web.mywebsite.enums.ResponseCode;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResponseDto<T> {
    private ResponseCode code;
    private T data;

    public CommonResponseDto(ResponseCode code) {
        this(code, null);
    }

    public CommonResponseDto(T data) {
        this(ResponseCode.SUCCESS, data);
    }
}