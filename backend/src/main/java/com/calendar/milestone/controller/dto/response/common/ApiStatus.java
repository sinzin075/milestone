package com.calendar.milestone.controller.dto.response.common;

public enum ApiStatus {
    OK(200,"Ok"),
    CREATE(201,"Create"),
    NO_CONTENT(204,"No Content"),
    BAD_REQUEST(400,"Bad Request"),
    UNAUTHORIZED(401,"Unauthorized"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Not Found"),
    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
    CONFLICT(409, "Conflict"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error");
    

    final private int status;
    final private String message;

    ApiStatus(final int status,final String message){
        this.status=status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }
    public String getMessage() {
        return message;
    }

}
