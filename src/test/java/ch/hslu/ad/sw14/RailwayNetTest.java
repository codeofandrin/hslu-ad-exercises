package ch.hslu.ad.sw14;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

class RailwayNetTest {

    private RailwayNet net;

    @BeforeEach
    void setUp() {
        net = new RailwayNet();
    }

    @Test
    void testGetStationsAmount() {
        assertThat(net.getStationsAmount()).isEqualTo(13);
    }

    @Test
    void testGetRoutesAmount() {
        assertThat(net.getRoutesAmount()).isEqualTo(24);
    }

    @Test
    void testGetTravelTimeDirect_aarauToLenzburg() {
        assertThat(net.getTravelTimeDirect("Aarau", "Lenzburg")).isEqualTo(8);
    }

    @Test
    void testGetTravelTimeDirect_isSymmetric() {
        int forward = net.getTravelTimeDirect("Aarau", "Lenzburg");
        int backward = net.getTravelTimeDirect("Lenzburg", "Aarau");
        assertThat(forward).isEqualTo(backward);
    }

    @Test
    void testGetTravelTimeDirect_sameStation() {
        assertThat(net.getTravelTimeDirect("Zürich", "Zürich")).isZero();
    }

    @Test
    void testGetTravelTimeDirect_noDirectRoute() {
        assertThatThrownBy(() -> net.getTravelTimeDirect("Aarau", "Luzern"))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("direct route does not exist");
    }

    @Test
    void testGetTravelTimeDirect_unknownStart() {
        assertThatThrownBy(() -> net.getTravelTimeDirect("Basel", "Zürich"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Basel");
    }

    @Test
    void testGetTravelTimeDirect_unknownEnd() {
        assertThatThrownBy(() -> net.getTravelTimeDirect("Zürich", "Basel"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Basel");
    }

    @Test
    void testGetTravelTimeDirect_nullStart() {
        assertThatThrownBy(() -> net.getTravelTimeDirect(null, "Zürich"))
                .isInstanceOf(Exception.class);
    }

    @Test
    void testGetTravelTimeDirect_nullEnd() {
        assertThatThrownBy(() -> net.getTravelTimeDirect("Zürich", null))
                .isInstanceOf(Exception.class);
    }

    @Test
    void testGetTravelTimeDirect_allDirectRoutes() {
        String[] stations = {
                "Aarau", "Arth-Goldau", "Brugg", "Dietikon", "Lenzburg", "Luzern",
                "Olten", "Pfäffikon", "Rotkreuz", "Wohlen", "Zofingen", "Zug", "Zürich"
        };

        for (String from : stations) {
            for (String to : stations) {
                try {
                    int time = net.getTravelTimeDirect(from, to);
                    assertThat(time)
                            .as("travel time from %s to %s", from, to)
                            .isGreaterThanOrEqualTo(0);
                } catch (IllegalStateException ignored) {
                    // no direct route — expected for some pairs
                }
            }
        }
    }

    @Test
    void testIsDirectRoute_aarauToLenzburg() {
        assertThat(net.isDirectRoute("Aarau", "Lenzburg")).isTrue();
    }

    @Test
    void testIsDirectRoute_aarauToLuzern() {
        assertThat(net.isDirectRoute("Aarau", "Luzern")).isFalse();
    }

    @Test
    void testIsDirectRoute_sameStation() {
        assertThat(net.isDirectRoute("Zürich", "Zürich")).isTrue();
    }

    @Test
    void testIsDirectRoute_unknownStart() {
        assertThatThrownBy(() -> net.isDirectRoute("Basel", "Zürich"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testIsDirectRoute_unknownEnd() {
        assertThatThrownBy(() -> net.isDirectRoute("Zürich", "Basel"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testIsDirectRoute_isSymmetric() {
        assertThat(net.isDirectRoute("Aarau", "Lenzburg"))
                .isEqualTo(net.isDirectRoute("Lenzburg", "Aarau"));
    }

    @Test
    void testGetStationsDirect_aarau() {
        assertThat(net.getStationsDirect("Aarau"))
                .containsExactlyInAnyOrder("Brugg", "Lenzburg", "Olten");
    }

    @Test
    void testGetStationsDirect_doesNotContainSelf() {
        assertThat(net.getStationsDirect("Aarau")).doesNotContain("Aarau");
    }

    @Test
    void testGetStationsDirect_unknownStation() {
        assertThatThrownBy(() -> net.getStationsDirect("Basel"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Basel");
    }

    @Test
    void testGetStationsDirect_nullStation() {
        assertThatThrownBy(() -> net.getStationsDirect(null))
                .isInstanceOf(Exception.class);
    }

    @Test
    void testGetStationsDirect_resultIsSymmetric() {
        // if Aarau -> Brugg exists, Brugg -> Aarau must also exist
        assertThat(net.getStationsDirect("Brugg")).contains("Aarau");
    }

}