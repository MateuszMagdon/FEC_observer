package com.example.mateu_000.fec_observer;

import android.os.AsyncTask;

import com.example.android.networkconnect.model.Position;
import com.example.android.networkconnect.model.Task;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by mateu_000 on 2015-05-17.
 */
public class Communicator {
    public static final String URI = "http://floodemergencycoordinator.apphb.com";


    private static String prepareContent(List<NameValuePair> contentList) {
        String body = "";
        for (NameValuePair pair: contentList) {
            if (!body.equals("")){
                body += "&";
            }
            body += pair.getName() + "=" + pair.getValue();
        }
        return body;
    }

    public static void postBackupRequest(Task taskToPost) {
        PostRequestTask task = new PostRequestTask(token);
        task.addContent(taskToPost.toJSONbackupRequest());
        JSONObject result = executeAsyncTakAndReturnResult(task, "/api/serviceUnit/backupRequest");
    }

    private static JSONObject executeAsyncTakAndReturnResult(AsyncTask<String, String, String> task, String url) {
        String result;
        JSONObject jObject = null;

        try {
            result = task.execute(URI + url).get();
            jObject = new JSONObject(result);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return jObject;
    }

    private static JSONArray executeAsyncTakAndReturnResultInArray(AsyncTask<String, String, String> task, String url) {
        String result;
        JSONArray jObject = null;

        try {
            result = task.execute(URI + url).get();
            jObject = new JSONArray(result);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return jObject;
    }

}
