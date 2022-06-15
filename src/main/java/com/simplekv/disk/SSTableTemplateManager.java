package com.simplekv.disk;

public class SSTableTemplateManager {

    private static AbstractSSTableTemplate ssTableTemplate;

    public static AbstractSSTableTemplate chooseDefaultSSTableTemplate() {
        ssTableTemplate = new SSTableTemplate();
        return ssTableTemplate;
    }
}
