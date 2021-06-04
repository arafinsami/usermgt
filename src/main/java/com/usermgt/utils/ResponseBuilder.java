package com.usermgt.utils;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

public class ResponseBuilder implements Response {

    private Object data;

    private Object error;

    private ResponseType type;

    public ResponseBuilder(ResponseType type) {
        this.type = type;
    }

    public static ResponseBuilder success(Object data) {
        ResponseBuilder response = new ResponseBuilder(ResponseType.DATA);
        response.data = data;
        return response;
    }

    public static ResponseBuilder error(Object error) {
        ResponseBuilder response = new ResponseBuilder(ResponseType.ERROR);
        response.error = error;
        return response;
    }

    @Override
    public JSONObject getJson() {

        Map<String, Object> maps = new HashMap<String, Object>();

		switch (this.type) {
			case DATA: maps.put("data", data);
			case ERROR: maps.put("error", error);
		}
		return new JSONObject(maps);
	}

}
