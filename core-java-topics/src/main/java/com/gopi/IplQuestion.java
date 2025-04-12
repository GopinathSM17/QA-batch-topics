package com.gopi;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class IplQuestion {

    // 1st question - Number of matches played per year of all the years in IPL.
    public static  HashMap matchesPlayedPerYear(JSONArray matches){
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < matches.length() ; i++) {
            JSONObject match  = matches.getJSONObject(i);
            if(map.containsKey(match.getString("season"))){
                map.put(match.getString("season"), map.get(match.getString("season")) + 1);
            }
            else {
                map.put(match.getString("season"), 1);
            }
        }

        for(String season : map.keySet()){
            System.out.println("In Season " + season + "the total matches played are " + map.get(season) );

        }
        return map;
    }

    // 2nd question - Number of matches won of all teams over all the years of IPL.
    public static HashMap allYearsAndWinnerInIpl(JSONArray matches){
        Map<String, Map<String, Integer>> seasonWins = new TreeMap<>();
        for (int i = 0; i < matches.length(); i++) {
            JSONObject match = matches.getJSONObject(i);
            String season = match.getString("season");
            String winner = match.optString("winner", "");

            if (!winner.isEmpty()) {
                if (!seasonWins.containsKey(season)) {
                    seasonWins.put(season, new HashMap<>());
                }

                Map<String, Integer> teamWins = seasonWins.get(season);
                if (teamWins.containsKey(winner)) {
                    teamWins.put(winner, teamWins.get(winner) + 1);
                } else {
                    teamWins.put(winner, 1);
                }
            }
        }

        for (String season : seasonWins.keySet()) {
            System.out.println("Season " + season + ":");
            Map<String, Integer> teamWins = seasonWins.get(season);
            for (String team : teamWins.keySet()) {
                System.out.println("  " + team + " -> " + teamWins.get(team) + " wins");
            }
            System.out.println();
        }

        return null;
    }

    // 3rd question - For the year 2016 get the extra runs conceded per team.
    public static Map extraRunsIn2016ByEachTeam(JSONArray matches, JSONArray deliveries){
        Map<String, Integer> map=new HashMap<>();

        for (int i = 0; i < deliveries.length(); i++) {
            JSONObject delivery = deliveries.getJSONObject(i);
            int match_id = delivery.getInt("match_id")-1;
            JSONObject match = matches.getJSONObject(match_id);
            int season = match.getInt("season");

            String bowlingTeam = delivery.getString("bowling_team");
            int extraRuns = delivery.getInt("extra_runs")  ;
            if(season == 2016){
                if(map.containsKey(bowlingTeam)){
                    map.put(bowlingTeam, map.get(bowlingTeam) + extraRuns );
                }
                else {
                    map.put(delivery.getString("bowling_team"), extraRuns);
                }
            }

        }


        for(String team : map.keySet()){
            System.out.println("In the year 2016 extra runs of team " + team + " conceded is " + map.get(team) );

        }

        return map;
    }

    // 4th question - For the year 2015 get the top economical bowlers.
    public static HashMap economicalBowlerIn2015(JSONArray matches, JSONArray deliveries){
        Map<String, Integer> bowlerBalls = new HashMap<>();
        Map<String, Integer> bowlerRuns = new HashMap<>();

        for (int i = 0; i < deliveries.length(); i++) {
            JSONObject delivery = deliveries.getJSONObject(i);
            int match_id = delivery.getInt("match_id") - 1;

            JSONObject match = matches.getJSONObject(match_id);
            int season = match.getInt("season");

            int runsInTheCurrentBall = delivery.getInt("total_runs");
            String bowlerName = delivery.getString("bowler");

            if(bowlerBalls.containsKey(bowlerName)){
                bowlerBalls.put(bowlerName, bowlerBalls.get(bowlerName) + 1);
            }
            else{
                bowlerBalls.put(bowlerName, 1);
            }

            if(bowlerRuns.containsKey(bowlerName)){
                bowlerRuns.put(bowlerName, bowlerRuns.get(bowlerName)+ runsInTheCurrentBall);
            }
            else{
                bowlerRuns.put(bowlerName, runsInTheCurrentBall);
            }
        }

        String bestBowler = null;
        double bestEconomy = Double.MAX_VALUE;

        for (String bowler : bowlerBalls.keySet()) {
            int balls = bowlerBalls.getOrDefault(bowler, 0);
            int runs = bowlerRuns.getOrDefault(bowler, 0);

            if (balls == 0) continue; // to avoid division by zero

            double overs = balls / 6.0;
            double economy = runs / overs;

            if (economy < bestEconomy) {
                bestEconomy = economy;
                bestBowler = bowler;
            }
        }

        System.out.println("Best Economy Bowler: " + bestBowler);
        System.out.println("Economy Rate: " + String.format("%.2f", bestEconomy));



        return null;
    }

    // 5th question -
}
