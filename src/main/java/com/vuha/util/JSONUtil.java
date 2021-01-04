package com.vuha.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSONUtil {

    private static final ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.ACCEPT_FLOAT_AS_INT, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    /**
     * Convert JsonArray To Obj List
     *
     * @param jsonArray
     * @param type
     * @param <T>
     * @return
     */
    public static <T> List<T> jsonArrayToObjList(JSONArray jsonArray, Class<T> type) {
        List<T> result = new ArrayList<T>();

        if (jsonArray.isEmpty()) {
            return result;
        }

        try {
            for (Object o : jsonArray) {
                result.add(mapper.readValue(o.toString(), type));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  result;
    }

    public static Object parseReaderFromPath(String path) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        FileReader reader = new FileReader(path);
        return jsonParser.parse(reader);
    }

}
