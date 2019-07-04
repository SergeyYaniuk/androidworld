package com.sergeyyaniuk.androidworld.data.remote;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.sergeyyaniuk.androidworld.data.model.ApiResponse;
import com.sergeyyaniuk.androidworld.data.model.Event;
import com.sergeyyaniuk.androidworld.data.model.Shop;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Deserializer to parse data from response. As we receiving different type of JsonObjects
 */
public class ResponseDeserializer implements JsonDeserializer<ApiResponse> {

    @Override
    public ApiResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        List<Event> events = new ArrayList<>();
        List<Shop> shops = new ArrayList<>();
        JsonArray jsonArray = json.getAsJsonArray();
        for (int i = 0; i<jsonArray.size(); i++){
            JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
            Gson gson=new Gson();
            String type = jsonObject.get("type").getAsString();
            if (type.equals("event")){
                Event event = gson.fromJson(jsonObject, Event.class);
                events.add(event);
            } else if (type.equals("shop")){
                Shop shop = gson.fromJson(jsonObject, Shop.class);
                shops.add(shop);
            }
        }
        ApiResponse response = new ApiResponse();
        response.setEvents(events);
        response.setShops(shops);
        return response;
    }
}
