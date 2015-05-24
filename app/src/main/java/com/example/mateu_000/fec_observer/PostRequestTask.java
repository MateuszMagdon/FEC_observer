package com.example.mateu_000.fec_observer;

import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mateu_000 on 2015-05-17.
 */
class PostRequestTask extends AsyncTask<String, String, String> {

    public static final String TAG = "Network Connect";

    private List<NameValuePair> headersList = new ArrayList<>();
    private String content;
    private String result;

    public PostRequestTask() {
    }

    public void addHeader(String name, String value) {
        headersList.add(new BasicNameValuePair(name, value));
    }

    public void addContent(String content) {
        this.content = content;
    }

    public String getResult() {
        return result;
    }

    @Override
    protected String doInBackground(String... uri) {
        HttpClient httpclient = new DefaultHttpClient();
        HttpResponse response;
        String responseString = null;
        try {
            HttpPost post = new HttpPost(uri[0]);

            for (NameValuePair pair : headersList) {
                post.addHeader(pair.getName(), pair.getValue());
            }

            //String content = prepareContent();
            post.setEntity(new StringEntity(content));

            post.addHeader("Content-Type", "application/json");
            post.addHeader("Accept", "application/json");

            response = httpclient.execute(post);
            StatusLine statusLine = response.getStatusLine();
            if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                response.getEntity().writeTo(out);
                responseString = out.toString();
                out.close();
            } else {
                //Closes the connection.
                response.getEntity().getContent().close();
                throw new IOException(statusLine.getReasonPhrase());
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseString;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        this.result = result;
    }
}
