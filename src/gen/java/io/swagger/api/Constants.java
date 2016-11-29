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
    public static final int NO_CONTENT = 404;
    public static final int MUST_BE_AUTH = 401;
    public static final int UNAUTHORIZE = 403;

    public static final String URL_IMGFLIP = "https://api.imgflip.com/get_memes";

}
