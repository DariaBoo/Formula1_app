package ua.foxminded.sorter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import ua.foxminded.exception.DAOException;
import ua.foxminded.racer.Racer;

class SorterTest {
    Sorter sorter = new Sorter();

    @Disabled("Not implemented yet")
    @Test
    void sort() {
        assertThrows(IllegalArgumentException.class, () -> sorter.sortByLapTime());
    }

    @Disabled("Not implemented yet")
    @Test
    void s() throws DAOException {
        List<Racer> racers = new ArrayList<Racer>();
        racers.add(new Racer("Sebastian Vettel", "FERRARI", 64415L));
        racers.add(new Racer("Daniel Ricciardo", "RED BULL RACING TAG HEUER", 72013L));
        racers.add(new Racer("Valtteri Bottas", "MERCEDES", 72434L));
        racers.add(new Racer("Lewis Hamilton", "MERCEDES", 72460L));
        racers.add(new Racer("Stoffel Vandoorne", "MCLAREN RENAULT", 72463L));
        racers.add(new Racer("Kimi Raikkonen", "FERRARI", 72639L));
        racers.add(new Racer("Fernando Alonso", "MCLAREN RENAULT", 72657L));
        racers.add(new Racer("Sergey Sirotkin", "WILLIAMS MERCEDES", 72706L));
        racers.add(new Racer("Charles Leclerc", "SAUBER FERRARI", 72829L));
        racers.add(new Racer("Sergio Perez", "FORCE INDIA MERCEDES", 72848L));
        racers.add(new Racer("Romain Grosjean", "HAAS FERRARI", 72930L));
        racers.add(new Racer("Pierre Gasly", "SCUDERIA TORO ROSSO HONDA", 72941L));
        racers.add(new Racer("Carlos Sainz", "RENAULT", 72950L));
        racers.add(new Racer("Esteban Ocon", "FORCE INDIA MERCEDES", 73028L));
        racers.add(new Racer("Nico Hulkenberg", "RENAULT", 73065L));
        racers.add(new Racer("Brendon Hartley", "SCUDERIA TORO ROSSO HONDA", 73179L));
        racers.add(new Racer("Marcus Ericsson", "SAUBER FERRARI", 73265L));
        racers.add(new Racer("Lance Stroll", "WILLIAMS MERCEDES", 73323L));
        racers.add(new Racer("Kevin Magnussen", "HAAS FERRARI", 73393L));
        assertEquals(racers, sorter.sortByLapTime());
    }
}
