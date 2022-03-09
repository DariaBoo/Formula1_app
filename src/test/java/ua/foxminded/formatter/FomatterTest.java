package ua.foxminded.formatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import ua.foxminded.dao.DAOException;
import ua.foxminded.dto.RacerDTO;

class FomatterTest {
    private Formatter formatter;

    @Test
    void formatResult_shoultReturnFormatedString_whenInputCorrectList() {
        List<RacerDTO> racers = new ArrayList<RacerDTO>();
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
        formatter = new Formatter(racers);
        String expected = "1.Sebastian Vettel |FERRARI                  |1:04.415\r\n"
                + "2.Daniel Ricciardo |RED BULL RACING TAG HEUER|1:12.013\r\n"
                + "3.Valtteri Bottas  |MERCEDES                 |1:12.434\r\n"
                + "4.Lewis Hamilton   |MERCEDES                 |1:12.460\r\n"
                + "5.Stoffel Vandoorne|MCLAREN RENAULT          |1:12.463\r\n"
                + "6.Kimi Raikkonen   |FERRARI                  |1:12.639\r\n"
                + "7.Fernando Alonso  |MCLAREN RENAULT          |1:12.657\r\n"
                + "8.Sergey Sirotkin  |WILLIAMS MERCEDES        |1:12.706\r\n"
                + "9.Charles Leclerc  |SAUBER FERRARI           |1:12.829\r\n"
                + "10.Sergio Perez    |FORCE INDIA MERCEDES     |1:12.848\r\n"
                + "11.Romain Grosjean |HAAS FERRARI             |1:12.930\r\n"
                + "12.Pierre Gasly    |SCUDERIA TORO ROSSO HONDA|1:12.941\r\n"
                + "13.Carlos Sainz    |RENAULT                  |1:12.950\r\n"
                + "14.Esteban Ocon    |FORCE INDIA MERCEDES     |1:13.028\r\n"
                + "15.Nico Hulkenberg |RENAULT                  |1:13.065\r\n"
                + "------------------------------------------------------\n"
                + "16.Brendon Hartley |SCUDERIA TORO ROSSO HONDA|1:13.179\r\n"
                + "17.Marcus Ericsson |SAUBER FERRARI           |1:13.265\r\n"
                + "18.Lance Stroll    |WILLIAMS MERCEDES        |1:13.323\r\n"
                + "19.Kevin Magnussen |HAAS FERRARI             |1:13.393\r\n";
        assertEquals(expected, formatter.formatResult());
    }

    @Test
    void formatResult_shoultReturnFormatedString_whenInputLessThan15Objects() {
        List<RacerDTO> racers = new ArrayList<RacerDTO>();
        racers.add(new RacerDTO("Sebastian Vettel", "FERRARI", 64415L));
        racers.add(new RacerDTO("Daniel Ricciardo", "RED BULL RACING TAG HEUER", 72013L));

        formatter = new Formatter(racers);
        String expected = "1.Sebastian Vettel|FERRARI                  |1:04.415\r\n"
                + "2.Daniel Ricciardo|RED BULL RACING TAG HEUER|1:12.013\r\n";

        assertEquals(expected, formatter.formatResult());
    }

    @Test
    void formatResult_shouldThrowNullPointerException() throws DAOException {
        formatter = new Formatter(new ArrayList<RacerDTO>());
        assertThrows(NullPointerException.class, () -> formatter.formatResult());
    }
}
