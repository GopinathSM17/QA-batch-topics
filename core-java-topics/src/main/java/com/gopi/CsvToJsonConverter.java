package com.gopi;

import com.opencsv.CSVReader;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileReader;
import java.util.List;

public class CsvToJsonConverter {
    public static JSONArray convert(String csvFile) {
//        String csvFile = filePath; // path to your CSV file
        JSONArray jsonArray = new JSONArray();
        try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
            List<String[]> rows = reader.readAll();

            String[] headers = rows.get(0); // First row as keys


            for (int i = 1; i < rows.size(); i++) {
                String[] row = rows.get(i);
                JSONObject jsonObject = new JSONObject();
                for (int j = 0; j < headers.length; j++) {
                    jsonObject.put(headers[j], row[j]);
                }
                jsonArray.put(jsonObject);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonArray;
    }
}
