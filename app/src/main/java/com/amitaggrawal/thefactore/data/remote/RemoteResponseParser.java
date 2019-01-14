package com.amitaggrawal.thefactore.data.remote;

import com.amitaggrawal.thefactore.data.local.MyEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Amit Aggrawal on 12-01-2019.
 */
public class RemoteResponseParser {

    private static final String ID = "id";

    private static final String TITLE = "title";

    private static final String COMPLETION_STATUS = "completed";

    private ArrayList<MyEntity> mEntitiesItems;

    RemoteResponse parse(String responseBody) throws JSONException {
        JSONObject jsonObject = new JSONObject(responseBody);
        MyEntity[] myEntities = fromJSON(jsonObject);
        return new RemoteResponse(myEntities);
    }

    private static MyEntity[] fromJSON(JSONObject jsonData) throws JSONException {
        JSONObject jsonObject = new JSONObject(String.valueOf(jsonData.getJSONObject("data").getJSONObject("survey")));
        return fromJSONArray(jsonObject);

    }

    private static MyEntity[] fromJSONArray(JSONObject innerJsonData) throws JSONException {
        JSONArray jsonArray = new JSONArray(String.valueOf(innerJsonData.getJSONArray("data")));

        MyEntity[] entities = new MyEntity[jsonArray.length()];

        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject jsonObject = jsonArray.getJSONObject(i);

            int id = jsonObject.getInt(ID);
            String title = jsonObject.getString(TITLE);
            String status = jsonObject.getString(COMPLETION_STATUS);

            entities[i] = new MyEntity(id, title, status);
        }

        return entities;
    }

}
