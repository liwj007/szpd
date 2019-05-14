/*
 * Copyright (c) 2014-2019, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.liwj.szpd.utils;
import java.io.Serializable;


public class JsonResult<T> implements Serializable {
    private static final long serialVersionUID = 3997124446365032582L;

    private String message;

    private T data;

    private Boolean state;

    /**
     * 错误，系统异常
     *
     * @return result
     */
    public static JsonResult renderError() {
        JsonResult result = new JsonResult();
        result.setState(Boolean.FALSE);
        return result;
    }

    /**
     * 错误数据（带消息）
     *
     * @param msg 需要返回的消息
     * @return result
     */
    public static JsonResult renderError(String msg) {
        JsonResult jsonResult = JsonResult.renderError();
        jsonResult.setMessage(msg);
        return jsonResult;
    }

    /**
     * 成功数据
     *
     * @return result
     */
    public static JsonResult renderSuccess() {
        JsonResult result = new JsonResult();
        result.setState(Boolean.TRUE);
        return result;
    }

    /**
     * 成功数据（带信息）
     *
     * @param msg 需要返回的信息
     * @return result
     */
    public static JsonResult renderSuccess(String msg) {
        JsonResult result = JsonResult.renderSuccess();
        result.setMessage(msg);
        return result;
    }

    /**
     * 成功数据（带数据）
     *
     * @param obj 需要返回的对象
     * @return result
     */
    public static JsonResult renderSuccess(Object obj) {
        JsonResult result = JsonResult.renderSuccess();
        result.setData(obj);
        return result;
    }

    /**
     * 成功数据（带数据,带信息）
     *
     * @param msg 需要返回的信息
     * @param obj 需要返回的对象
     * @return result
     */
    public static JsonResult renderSuccess(String msg, Object obj) {
        JsonResult result = JsonResult.renderSuccess();
        result.setMessage(msg);
        result.setData(obj);
        return result;
    }

    /**
     * 失败数据
     *
     * @return result
     */
    public static JsonResult renderFail() {
        JsonResult result = new JsonResult();
        result.setState(Boolean.FALSE);
        return result;
    }

    /**
     * 失败数据（带消息）
     *
     * @param msg 需要返回的消息
     * @return result
     */
    public static JsonResult renderFail(String msg) {
        JsonResult result = JsonResult.renderFail();
        result.setMessage(msg);
        return result;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }
}

