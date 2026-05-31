package ch.hslu.ad.sw14;

public class Main {

    static void main() {
        RailwayNet net = new RailwayNet();

        int travelTimeDirect = net.getShortestTravelTime("Olten", "Pfäffikon");
        System.out.println("Shortest time from Olten to Pfäffikon: " + travelTimeDirect);

        int travelTimeRK1 = net.getShortestTravelTime("Olten", "Rotkreuz");
        int travelTimeRK2 = net.getShortestTravelTime("Rotkreuz", "Pfäffikon");

        System.out.println("Shortest time from Olten via Rotkreuz to Pfäffikon: " + (travelTimeRK1 + travelTimeRK2));

    }
}
