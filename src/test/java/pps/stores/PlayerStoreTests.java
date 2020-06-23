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
}
