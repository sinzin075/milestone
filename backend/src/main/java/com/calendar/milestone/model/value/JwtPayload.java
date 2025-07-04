package com.calendar.milestone.model.value;

import com.calendar.milestone.model.entity.User;

public class JwtPayload {
    private final int payloadId;

    private JwtPayload(final User user) {
        payloadId = user.getId();
    }

    public static JwtPayload of(final User user) {
        return new JwtPayload(user);
    }

    public String getPayloadId() {
        return String.valueOf(payloadId);
    }
}
