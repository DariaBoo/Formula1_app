package ua.foxminded.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import ua.foxminded.dto.RacerDTO;

@ExtendWith(MockitoExtension.class)
class DAOSorterTest {

    @Disabled("Not implemented yet")
    @Test
    void sortByLapTime() throws DAOException {
        DAOSorter daoSorter = new DAOSorter();

        List<RacerDTO> racers = new ArrayList<>();
        racers.add(new RacerDTO("Sebastian Vettel", "FERRARI", 64415L));
        racers.add(new RacerDTO("Daniel Ricciardo", "RED BULL RACING TAG HEUER", 72013L));
        racers.add(new RacerDTO("Valtteri Bottas", "MERCEDES", 72434L));
        racers.add(new RacerDTO("Lewis Hamilton", "MERCEDES", 72460L));
        racers.add(new RacerDTO("Stoffel Vandoorne", "MCLAREN RENAULT", 72463L));
        racers.add(new RacerDTO("Kimi Raikkonen", "FERRARI", 72639L));
        racers.add(new RacerDTO("Fernando Alonso", "MCLAREN RENAULT", 72657L));
        racers.add(new RacerDTO("Sergey Sirotkin", "WILLIAMS MERCEDES", 72706L));
        racers.add(new RacerDTO("Charles Leclerc", "SAUBER FERRARI", 72829L));
        racers.add(new RacerDTO("Sergio Perez", "FORCE INDIA MERCEDES", 72848L));
        racers.add(new RacerDTO("Romain Grosjean", "HAAS FERRARI", 72930L));
        racers.add(new RacerDTO("Pierre Gasly", "SCUDERIA TORO ROSSO HONDA", 72941L));
        racers.add(new RacerDTO("Carlos Sainz", "RENAULT", 72950L));
        racers.add(new RacerDTO("Esteban Ocon", "FORCE INDIA MERCEDES", 73028L));
        racers.add(new RacerDTO("Nico Hulkenberg", "RENAULT", 73065L));
        racers.add(new RacerDTO("Brendon Hartley", "SCUDERIA TORO ROSSO HONDA", 73179L));
        racers.add(new RacerDTO("Marcus Ericsson", "SAUBER FERRARI", 73265L));
        racers.add(new RacerDTO("Lance Stroll", "WILLIAMS MERCEDES", 73323L));
        racers.add(new RacerDTO("Kevin Magnussen", "HAAS FERRARI", 73393L));

        assertEquals(racers, daoSorter.sortByLapTime());
    }
}
