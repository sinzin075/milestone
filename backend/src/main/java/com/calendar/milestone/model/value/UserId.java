package com.calendar.milestone.model.value;

public class UserId {
    final private int userId;
    final private static int MIN_VALUE = 1;

    private UserId(int userId) {
        this.userId = userId;
    }

    public static UserId of(int userId) {
        if (userId < MIN_VALUE) {
            throw new IllegalArgumentException("Value is too small. Argument is " + userId);
        }
        return new UserId(userId);
    }

    public int getValue() {
        return userId;
    }
}
