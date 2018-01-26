package com.jczc.operatorweb.exception;


/**
 * Created by lwj on 2017/10/26.
 */
public class DataException extends Exception {
    private ExceptionResponse response;

    public DataException(DataExceptionEnum exception) {
        response = ExceptionResponse.create(exception.description);
    }

    public ExceptionResponse getResponse() {
        return response;
    }

}
