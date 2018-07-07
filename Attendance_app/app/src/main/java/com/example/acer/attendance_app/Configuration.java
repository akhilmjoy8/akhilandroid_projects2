package com.example.acer.attendance_app;

public class Configuration {
    public static final String APP_SCRIPT_WEB_APP_URL = "https://script.google.com/macros/s/AKfycbyHdpNv_AtQu4PJSGcuK8hdZ2u7PhKACdIDp4r6hWn50PWICoM/exec";
   // public static final String APP_SCRIPT_WEB_APP_URL = "https://script.google.com/macros/s/AKfycbzK9ppx8Xxtb-QBvlPagpA-P-0qKJg2GuYxazRdE3eJcgVkEHI/exec";
    public static final String ADD_USER_URL = APP_SCRIPT_WEB_APP_URL;
    public static final String LIST_USER_URL = APP_SCRIPT_WEB_APP_URL+"?action=readAll";

    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_COUNTRY = "country";


    public static final String KEY_USERS = "records";

}
