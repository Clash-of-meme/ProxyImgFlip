package io.swagger.api;/**
 * Created by Guillaume on 29/11/2016.
 */

import org.apache.log4j.Logger;

/**
 * Classe non instanciable référencant les constante du service ProxyImgFlip
 */
public class Constants {

    private static final Logger log = Logger.getLogger(Constants.class);

    public static final int ERROR = 503;
    public static final int OK = 200;
    public static final int CREATED = 201;
    public static final int BAD_REQUEST = 400;
    public static final int NOT_FOUND = 404;
    public static final int FORBIDDEN = 401;
    public static final int UNAUTHORIZE = 403;

    public static final String API_LOGIN ="ProxyImgFlip";
    public static final String API_PASSWORD ="epsi2016";

    public static final String URL_IMGFLIP = "https://api.imgflip.com/get_memes";
    public static final String PROTOCOLE = "https";
    public static final String HOST = "api.imgflip.com";
    public static final String URL_ENDPOINT_POST = "/caption_image";

    public static final String LOGIN = "clashofmeme";
    public static final String PASSWORD = "epsi2016";
}
