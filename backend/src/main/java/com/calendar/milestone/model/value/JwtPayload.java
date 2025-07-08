package com.calendar.milestone.model.value;



public class JwtPayload {
    private final int payloadId;

    private JwtPayload(final UserId userId) {
        payloadId = userId.getValue();
    }

    public static JwtPayload of(final UserId userId) {
        return new JwtPayload(userId);
    }

    public String getPayloadId() {
        return String.valueOf(payloadId);
    }
}
