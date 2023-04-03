package ru.netology;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String json = readString("new_data.json");
        List<Employee> list = jsonToList(json);
        for (Employee employee : list)
            System.out.println(employee);
    }

    public static String readString(String fileName) {
        StringBuilder result = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                result.append(inputLine);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return result.toString();
    }

    public static List<Employee> jsonToList(String json) {
        List<Employee> employeeList = new ArrayList<>();
        try {
            JSONParser jsonParser = new JSONParser();
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();

            JSONArray jsonArray = (JSONArray) jsonParser.parse(json);
            for (Object jsonText : jsonArray){
                String jt = String.valueOf(jsonText);
                employeeList.add(gson.fromJson(jt, Employee.class));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return employeeList;
    }
}