package com.als.score.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    private int code;
    private String message;
    private Object data;

    /**
     * 成功返回结果
     * @param message
     * @return
     */
    public static Response success(String message) {
        return new Response(200, message, null);
    }

    /**
     * 成功返回结果
     * @param message
     * @return
     */
    public static Response success(String message, Object data) {
        return new Response(200, message, data);
    }

    /**
     * 失败返回结果
     * @param message
     * @return
     */
    public static Response error(String message) {
        return new Response(500, message, null);
    }

    /**
     * 失败返回结果
     * @param message
     * @return
     */
    public static Response error(String message, Object data) {
        return new Response(500, message, data);
    }

}
