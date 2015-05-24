package com.example.mateu_000.fec_observer;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by mateu_000 on 2015-05-24.
 */
public class ObserverReport {
    public int ObserverId;
    public String Description;
    public Position Position;

    public ObserverReport(String description, Position position) {
        ObserverId = 1;
        Description = description;
        Position = position;
    }

    public String toJSON() {
        JSONObject object = new JSONObject();
        try {
            object.put("ObserverId", ObserverId);
            object.put("Description", Description);
            object.put("Position", Position.toJSON());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object.toString();
    }
}
