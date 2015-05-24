package com.example.mateu_000.fec_observer;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

/**
 * Created by mateu_000 on 2015-05-17.
 */
public class Communicator {
    public static final String URI = "http://floodemergencycoordinator.apphb.com";

    public static void postBackupRequest(ObserverReport report) {
        PostRequestTask task = new PostRequestTask();
        task.addContent(report.toJSON());
        JSONObject result = executeAsyncTakAndReturnResult(task, "/api/observer/observerReport");
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

}
