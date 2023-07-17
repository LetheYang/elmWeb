package com.elm.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author akemihomurasama
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result implements Serializable {
    public Boolean result;
    public Integer code;
    public String message;
    public Object data;

    public static Result ok() {
        return new Result(true, null, null, null);
    }

    public static Result ok(Object data) {
        return new Result(true, null, null, data);
    }

    public static Result fail(String errorMsg) {
        return new Result(false, null, errorMsg, null);
    }
}
