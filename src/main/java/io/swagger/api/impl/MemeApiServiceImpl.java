package io.swagger.api.impl;

import io.swagger.api.*;

import io.swagger.api.Constants;
import io.swagger.model.MemeReturn;
import io.swagger.model.MemePattern;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import io.swagger.api.NotFoundException;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-11-27T10:15:06.921Z")
public class MemeApiServiceImpl extends MemeApiService {

    private static final Logger logger = LogManager.getLogger(MemeApiServiceImpl.class);
    private int status;

    @Override
    public Response memeGet(SecurityContext securityContext) throws NotFoundException {

        ArrayList<MemeReturn> listOfMemes = new ArrayList<>();



        logger.info("Appel du service : " + Constants.URL_IMGFLIP);

        try {

            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet(Constants.URL_IMGFLIP);

            HttpResponse response = client.execute(request);
            logger.info("Le code retour du service est : " + response.getStatusLine().getStatusCode());
            if(response.getStatusLine().getStatusCode() == Constants.OK) {
                String result = EntityUtils.toString(response.getEntity());

                JSONObject res_json = new JSONObject(result);
                JSONObject data = (JSONObject) res_json.get("data");
                JSONArray listOfMemes_imgflip = (JSONArray) data.get("memes");

                // ITERAte THROUGH AND RETRIEVE CLUB FIELDS
                int n = listOfMemes_imgflip.length();
                for (int i = 0; i < n; i++) {
                    // GET INDIVIDUAL JSON OBJECT FROM JSON ARRAY
                    JSONObject meme_img = listOfMemes_imgflip.getJSONObject(i);
                    MemeReturn meme = new MemeReturn();
                    meme.setName(meme_img.getString("name"));
                    meme.setUrl(meme_img.getString("url"));
                    meme.setIdImgflip(meme_img.getString("id"));
                    listOfMemes.add(meme);
                }
                status = Constants.OK;
            }
            else
            {
                Service_Error();
            }
        } catch (IOException e) {
           Service_Error();
        }
        return Response.status(status).entity(listOfMemes).build();
    }

    @Override
    public Response memePost(MemePattern id, SecurityContext securityContext) throws NotFoundException {

        String url = "";
        HttpPost request = new HttpPost(url);
        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("sn", "C02G8416DRJM"));
        urlParameters.add(new BasicNameValuePair("cn", ""));

        //request.setEntity(new UrlEncodedFormEntity(urlParameters));

        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    private void Service_Error(){
        status = Constants.ERROR;
        logger.info("Le service : "+Constants.URL_IMGFLIP + " n'est pas disponible");
    }

    private void Service_Warning(){
        status = Constants.ERROR;
        logger.info("Le service : "+Constants.URL_IMGFLIP + " ne retourne pas de résultat.");
    }
}
