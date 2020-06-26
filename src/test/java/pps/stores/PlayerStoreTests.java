package pps.stores;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerStoreTests {
    @BeforeEach public void resetTests() {
        PlayerStore.instance().reset();
    }

    @Test public void testInstance() {
        PlayerStore store = PlayerStore.instance();
        assertNotNull(store);
    }

    @Test public void testAddPlayers() {
        PlayerStore.instance().addPlayer("daniele");
        Set<String> players = PlayerStore.instance().getPlayers();
        Optional<String> player =
                players.stream().filter(f -> f.equals("daniele")).findFirst();
        assertTrue(player.isPresent());
        assertEquals("daniele", player.get());
    }

    @Test public void testDeletePlayers() {
        testAddPlayers();
        PlayerStore.instance().deletePlayer("daniele");
        Optional<String> player = PlayerStore.instance().getPlayers()
                .stream().filter(f -> f.equals("daniele")).findFirst();
        assertTrue(player.isEmpty());
    }

    @Test public void testAddAndRemoveManyPlayers() {
        PlayerStore.instance().addPlayer("Daniele");
        PlayerStore.instance().addPlayer("Stefano");
        PlayerStore.instance().addPlayer("Alberto");
        PlayerStore.instance().addPlayer("Lorenzo");
        PlayerStore.instance().addPlayer("Mirko");
        PlayerStore.instance().addPlayer("Alessandro");
        PlayerStore.instance().addPlayer("Angelo");
        PlayerStore.instance().addPlayer("Roberto");

        Optional<String> daniele = PlayerStore.instance().getPlayers()
                .stream().filter(f -> f.equals("Daniele")).findFirst();
        assertTrue(daniele.isPresent());
        assertEquals("Daniele", daniele.get());

        Optional<String> stefano = PlayerStore.instance().getPlayers()
                .stream().filter(f -> f.equals("Stefano")).findFirst();
        assertTrue(stefano.isPresent());
        assertEquals("Stefano", stefano.get());

        Optional<String> mirko = PlayerStore.instance().getPlayers()
                .stream().filter(f -> f.equals("Mirko")).findFirst();
        assertTrue(mirko.isPresent());
        assertEquals("Mirko", mirko.get());

        Optional<String> roberto = PlayerStore.instance().getPlayers()
                .stream().filter(f -> f.equals("Roberto")).findFirst();
        assertTrue(roberto.isPresent());
        assertEquals("Roberto", roberto.get());

        PlayerStore.instance().deletePlayer("Daniele");
        Optional<String> delDaniele = PlayerStore.instance().getPlayers()
                .stream().filter(f -> f.equals("Daniele")).findFirst();
        assertTrue(delDaniele.isEmpty());

        PlayerStore.instance().deletePlayer("Lorenzo");
        Optional<String> delLorenzo = PlayerStore.instance().getPlayers()
                .stream().filter(f -> f.equals("Lorenzo")).findFirst();
        assertTrue(delLorenzo.isEmpty());

        PlayerStore.instance().deletePlayer("Alberto");
        Optional<String> delAlberto = PlayerStore.instance().getPlayers()
                .stream().filter(f -> f.equals("Alberto")).findFirst();
        assertTrue(delAlberto.isEmpty());
    }
}
