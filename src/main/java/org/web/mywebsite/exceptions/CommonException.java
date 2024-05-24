package org.web.mywebsite.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.web.mywebsite.enums.ResponseCode;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class CommonException extends RuntimeException {
    ResponseCode code;

    public CommonException(ResponseCode code, String message) {
        super(message);
        this.code = code;
    }

    public CommonException(ResponseCode code) {
        this.code = code;
    }
}
