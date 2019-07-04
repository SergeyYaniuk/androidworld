package com.sergeyyaniuk.androidworld.data.model;

import java.util.List;

/**
 * Model class for a ApiResponse consists of {@link Event}s and {@link Shop}s
 */
public class ApiResponse {

    private List<Event> events;
    private List<Shop> shops;

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<Shop> getShops() {
        return shops;
    }

    public void setShops(List<Shop> shops) {
        this.shops = shops;
    }
}
