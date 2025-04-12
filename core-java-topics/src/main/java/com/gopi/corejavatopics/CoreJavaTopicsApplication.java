package com.gopi.corejavatopics;

import com.gopi.CsvToJsonConverter;
import com.gopi.CsvToJsonConverterForDeliveries;
import com.gopi.IplQuestion;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.json.JSONArray;

@SpringBootApplication
public class CoreJavaTopicsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoreJavaTopicsApplication.class, args);

        String filePath = "/Users/mahesh/Documents/QA-batch-topics/core-java-topics/src/main/java/com/gopi/csvFiles/matches.csv";
        JSONArray matches = CsvToJsonConverter.convert(filePath);

        String filePathForCountries = "/Users/mahesh/Documents/QA-batch-topics/core-java-topics/src/main/java/com/gopi/csvFiles/deliveries.csv";
        JSONArray deliveries = CsvToJsonConverterForDeliveries.convert(filePathForCountries);



        IplQuestion.matchesPlayedPerYear(matches);

        IplQuestion.allYearsAndWinnerInIpl(matches);

        IplQuestion.extraRunsIn2016ByEachTeam(matches, deliveries);

        IplQuestion.economicalBowlerIn2015(matches, deliveries);

        IplQuestion.mostPlayerOfTheMatch(matches);

        IplQuestion.mostPlayerOfTheMatchInSpecificYear(matches, 2010);

    }

}
