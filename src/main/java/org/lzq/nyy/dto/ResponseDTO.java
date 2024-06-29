package org.lzq.nyy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {
    private Boolean success;
    private String message;
    private Object data;

    public static final ResponseDTO SUCCESS;
    public static final ResponseDTO FAIL;
    static {
        SUCCESS = ResponseDTO.success(null);
        FAIL = ResponseDTO.fail(null);
    }

    public static ResponseDTO fail(String message) {
        return new ResponseDTO(false, message, null);
    }
    public static ResponseDTO success(String message, Object data) {
        return new ResponseDTO(true, message, data);
    }
    public static ResponseDTO success(String message) {
        return new ResponseDTO(true, message, null);
    }
}
