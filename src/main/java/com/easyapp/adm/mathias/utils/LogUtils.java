package com.easyapp.adm.mathias.utils;

import com.easyapp.adm.mathias.MathiasApplication;
import org.slf4j.LoggerFactory;

public class LogUtils {
    public static void log(String msn){
        LoggerFactory.getLogger(MathiasApplication.class).info(msn);
    }
}
