package io.swagger.api.impl;

import io.swagger.api.Constants;
import io.swagger.api.MemeApiService;
import io.swagger.api.NotFoundException;
import io.swagger.model.MemePattern;
import io.swagger.model.MemeReturn;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.io.IOException;
import java.util.ArrayList;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-11-27T10:15:06.921Z")
public class MemeApiServiceImpl extends MemeApiService {

    private static final Logger logger = LogManager.getLogger(MemeApiServiceImpl.class);


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
                return Response.status(Constants.OK).entity(listOfMemes).build();
            }
        } catch (IOException e) {
           Service_Error(e);
        }
        return Response.status(Constants.ERROR).build();
    }

    @Override
    public Response memePost(MemePattern id, SecurityContext securityContext) throws NotFoundException {

        MemeReturn meme = new MemeReturn();
        URIBuilder builder = new URIBuilder();
        builder.setScheme(Constants.PROTOCOLE).setHost(Constants.HOST).setPath(Constants.URL_ENDPOINT_POST)
                .setParameter("username", Constants.LOGIN)
                .setParameter("password", Constants.PASSWORD);

        logger.info("Appel du service : " + builder.toString());

        builder.setParameter("template_id", id.getIdImgflip())
                .setParameter("text0", id.getTextTop())
                .setParameter("text1", id.getTextBottom());

        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost request = new HttpPost(builder.toString());
            HttpResponse response = client.execute(request);
            logger.info("Le code retour du service est : " + response.getStatusLine().getStatusCode());

            if (response.getStatusLine().getStatusCode() == Constants.OK) {

                String result = EntityUtils.toString(response.getEntity());
                JSONObject res_json = new JSONObject(result);

                if(res_json.has("data")) {
                    JSONObject data = (JSONObject) res_json.getJSONObject("data");
                    meme.setIdImgflip(id.getIdImgflip());
                    meme.setName(id.getName());
                    meme.setUrl(data.getString("url"));
                    return Response.status(Constants.CREATED).entity(meme).build();
                }
                else{
                    logger.info("L'id fourni n'existe pas");
                    return Response.status(Constants.NOT_FOUND).build();
                }

            }
        } catch (IOException e) {
            Service_Error(e);
        }
        return Response.status(Constants.ERROR).build();
    }

    private void Service_Error(IOException e){
        logger.info("Le service : "+Constants.URL_IMGFLIP + " n'est pas disponible");
        logger.info("Error : " + e);
    }
}
