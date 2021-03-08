package com.example.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @Author wxy
 * @Date 2021/3/8 11:46
 * @Version 1.0
 */
@Slf4j
public final class LogPrintUtils {

    public static void errInfo(Exception e) {
        String str = "";
        try (StringWriter sw = new StringWriter(); PrintWriter pw = new PrintWriter(sw)) {
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();
            str = sw.toString();
        } catch (IOException ie) {
            log.error(ie.getMessage());
        }
        log.warn(str);
    }
}
