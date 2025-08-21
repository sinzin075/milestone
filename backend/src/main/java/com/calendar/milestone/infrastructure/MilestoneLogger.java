package com.calendar.milestone.infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MilestoneLogger {
    // メソッド名の取得についてはコスト高であるためinfoについてはメソッド名は表示なし。
    public static void info(String message) {
        StackTraceElement element = Thread.currentThread().getStackTrace()[2];
        String className = element.getClassName();
        Logger logger = LoggerFactory.getLogger(className);
        logger.info("[{}] {}", className, message);
    }

    public static void warn(String message) {
        StackTraceElement element = Thread.currentThread().getStackTrace()[2];
        String className = element.getClassName();
        String methodName = element.getMethodName();
        Logger logger = LoggerFactory.getLogger(className);
        logger.warn("[{}({})] {}", className, methodName, message);
    }

    public static void error(String message, Throwable t) {
        StackTraceElement element = Thread.currentThread().getStackTrace()[2];
        String className = element.getClassName();
        String methodName = element.getMethodName();
        Logger logger = LoggerFactory.getLogger(className);
        logger.error("[{}({})] {}", className, methodName, message, t);
    }

}
