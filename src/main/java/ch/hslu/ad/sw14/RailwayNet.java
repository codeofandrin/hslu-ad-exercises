package ch.hslu.ad.sw14;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class RailwayNet {
    private static final int INF = Integer.MAX_VALUE;

    private final String[] stations = {
            "Aarau",
            "Arth-Goldau",
            "Brugg",
            "Dietikon",
            "Lenzburg",
            "Luzern",
            "Olten",
            "Pfäffikon",
            "Rotkreuz",
            "Wohlen",
            "Zofingen",
            "Zug",
            "Zürich"
    };

    private final int[][] adjaMatrix = {
        // AAR  A-G   BG   DK  LEN   LU  OLT  PFA   RK  WOH  ZOF   ZG   ZH
        {  0,   INF,  13, INF,   8, INF,  13, INF, INF, INF, INF, INF, INF }, // AAR
        {INF,     0, INF, INF, INF,  30, INF,  39,  15, INF, INF,  20, INF }, // A-G
        { 13,   INF,   0,  16,  16, INF, INF, INF, INF, INF, INF, INF, INF }, // BG
        {INF,   INF,  16,   0,  19, INF, INF, INF, INF,  30, INF, INF,  12 }, // DK
        {  8,   INF,  16,  19,   0,  80, INF, INF, INF,   9,  34, INF,  19 }, // LEN
        {INF,    30, INF, INF,  80,   0, INF, INF,  16, INF,  35, INF, INF }, // LU
        { 13,   INF, INF, INF, INF, INF,   0, INF, INF, INF,   7, INF,  36 }, // OLT
        {INF,    39, INF, INF, INF, INF, INF,   0, INF, INF, INF, INF,  30 }, // PFA
        {INF,    15, INF, INF, INF,  16, INF, INF,   0,  23, INF,  12, INF }, // RK
        {INF,   INF, INF,  30,   9, INF, INF, INF,  23,   0, INF, INF, INF }, // WOH
        {INF,   INF, INF, INF,  34,  35,   7, INF, INF, INF,   0, INF, INF }, // ZOF
        {INF,    20, INF, INF, INF, INF, INF, INF,  12, INF, INF,   0,  25 }, // ZG
        {INF,   INF, INF,  12,  19, INF,  36,  30, INF, INF, INF,  25,   0 }  // ZH
    };

    private List<String> stationsAsList() {
        return List.of(this.stations);
    }

    public boolean isDirectRoute(final String start, final String end) {
        try {
            this.getTravelTimeDirect(start, end);
        } catch (IllegalStateException e) {
            return false;
        }

        return true;
    }

    public int getStationsAmount() {
        return this.stations.length;
    }

    public int getRoutesAmount() {
        int count = 0;

        for (int[] routes : adjaMatrix) {
            for (int t : routes) {
                if (t != 0 && t != INF) {
                    count++;
                }
            }
        }

        // routes are listed twice in adjacent matrix
        return count / 2;
    }

    public int getTravelTimeDirect(final String start, final String end) {
        List<String> stationsAsList = this.stationsAsList();

        if (!stationsAsList.contains(start)) {
            throw new IllegalArgumentException(String.format("station '%s' does not exist", start));
        }

        if (!stationsAsList.contains(end)) {
            throw new IllegalArgumentException(String.format("station '%s' does not exist", end));
        }

        int startIndex = stationsAsList.indexOf(start);
        int endIndex = stationsAsList.indexOf(end);

        int[] routes = adjaMatrix[startIndex];
        int travelTime = routes[endIndex];

        if (travelTime == INF) {
            throw new IllegalStateException("direct route does not exist");
        }

        return travelTime;
    }

    public String[] getStationsDirect(final String station) {
        List<String> stationsAsList = this.stationsAsList();

        if (!stationsAsList.contains(station)) {
            throw new IllegalArgumentException(String.format("station '%s' does not exist", station));
        }

        int startIndex = stationsAsList.indexOf(station);
        int[] routes = adjaMatrix[startIndex];

        List<String> stations = new ArrayList<>();
        int j = 0;
        for (int i = 0; i < routes.length; i++) {
            int t = routes[i];
            if (t != 0 && t != INF) {
                stations.add(stationsAsList.get(i));
                j++;
            }
        }

        return stations.toArray(new String[0]);
    }

    private int[][] deepCopy(int[][] original) {
        int[][] copy = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            copy[i] = Arrays.copyOf(original[i], original[i].length);
        }
        return copy;
    }

    private int[][] floydWarshall() {
        int nodeCount = this.adjaMatrix.length;
        int[][] distance = deepCopy(this.adjaMatrix);
        for (int k = 0; k < nodeCount; k++) {
            for (int i = 0; i < nodeCount; i++) {
                for (int j = 0; j < nodeCount; j++) {
                    if (distance[i][k] < Integer.MAX_VALUE &&
                            distance[k][j] < Integer.MAX_VALUE &&
                            distance[i][k] + distance[k][j] < distance[i][j]) {
                        distance[i][j] = distance[i][k] + distance[k][j];
                    }
                }
            }
        }

        return distance;
    }

    public int getShortestTravelTime(final String start, final String end) {
        List<String> stationsAsList = this.stationsAsList();

        if (!stationsAsList.contains(start)) {
            throw new IllegalArgumentException(String.format("station '%s' does not exist", start));
        }

        if (!stationsAsList.contains(end)) {
            throw new IllegalArgumentException(String.format("station '%s' does not exist", end));
        }

        int startIndex = stationsAsList.indexOf(start);
        int endIndex = stationsAsList.indexOf(end);

        int[][] shortestTimes = floydWarshall();

        return shortestTimes[startIndex][endIndex];
    }

}
