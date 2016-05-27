package pl.edu.pja.gdansk.voyage2.error.domain;

public class RestErrorCode {

    public static final int ITEM_ALREADY_EXISTS_IN_THE_DATABASE = 1;
    public static final int ROUTE_NOT_FOUND_IN_THE_DATABASE = 2;
    public static final int ROUTE_ID_FROM_PATH_DOES_NOT_MATCH_TO_THE_ONE_FROM_BODY = 3;
    public static final int ROUTE_ACCESS_DENIED = 4;
    public static final int USER_NOT_FOUND = 5;
    public static final int SENDING_ACTIVATION_LINK_FAILED = 6;
    public static final int SENDING_NEW_PASSWORD_FAILED = 7;
    public static final int UPLOAD_FILE_FAILED = 8;
    public static final int SENDING_EMAIL_FAILED = 9;
    public static final int EMAIL_IS_ALREADY_USED = 10;
    public static final int USERNAME_IS_ALREADY_USED = 11;
    public static final int FOLDER_NAME_IS_ALREADY_USED = 12;
    public static final int ROUTE_IS_NOT_IN_YOUR_FOLDER = 13;
    public static final int FOLDER_NOT_FOUND = 14;
    public static final int ATTACHMENT_NOT_FOUND = 15;
    public static final int ROUTE_IS_ALREADY_FAVORITE = 16;
    public static final int ACCOUNT_IS_NOT_ACTIVE = 17;
    public static final int SENDING_SOS_FAILED = 18;
    public static final int USER_HAS_NOT_SET_SOS_EMAIL = 19;

}
