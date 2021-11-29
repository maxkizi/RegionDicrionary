package org.maxkizi.regiondictionary.controller;

public class ControllerUtils {
    public static final String BASE_URL = "/api/v1";
    public static final String BY_ID = "/{id}";


    public static final String REGION_CONTROLLER = BASE_URL + "/regions";
    public static final String REGION_CONTROLLER_BY_ID = REGION_CONTROLLER + BY_ID;

    private ControllerUtils() {
    }
}
