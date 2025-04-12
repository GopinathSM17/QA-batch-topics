package com.gopi;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;


public class CsvToJsonConverterForDeliveries {
    public static JSONArray convert(String filePath) {

        JSONArray jsonArray = new JSONArray();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            String[] headers = br.readLine().split(",");

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",", -1); // -1 keeps empty fields
                JSONObject obj = new JSONObject();

                for (int i = 0; i < headers.length && i < values.length; i++) {
                    obj.put(headers[i], values[i]);
                }

                jsonArray.put(obj);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonArray;
    }
}
