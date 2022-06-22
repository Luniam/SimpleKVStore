package com.simplekv.disk;

public class SSTableTemplateFactory {

    private static AbstractSSTableTemplate ssTableTemplate;

    public static AbstractSSTableTemplate getDefaultSSTableTemplate() {
        ssTableTemplate = new SSTableTemplate();
        return ssTableTemplate;
    }
}
