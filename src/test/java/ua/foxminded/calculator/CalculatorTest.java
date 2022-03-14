package ua.foxminded.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import ua.foxminded.exception.DAOException;

class CalculatorTest {

    Calculator calculator = new Calculator();

    @Disabled("Not implemented yet")
    @Test
    void countLapTime_shouldThrowNPE_whenReadAFileContainsNullValues() {
        assertThrows(NullPointerException.class, () -> calculator.countLapTime());
    }

    @Test
    void countLapTime_shouldReturnFilledMap() throws DAOException {
        Map<String, Long> expected = new HashMap<>();
        expected.put("PGS", 72941L);
        expected.put("SSW", 72706L);
        expected.put("FAM", 72657L);
        expected.put("CLS", 72829L);
        expected.put("LHM", 72460L);
        expected.put("MES", 73265L);
        expected.put("RGH", 72930L);
        expected.put("SPF", 72848L);
        expected.put("LSW", 73323L);
        expected.put("DRR", 72013L);
        expected.put("NHR", 73065L);
        expected.put("CSR", 72950L);
        expected.put("KMH", 73393L);
        expected.put("BHS", 73179L);
        expected.put("SVM", 72463L);
        expected.put("KRF", 72639L);
        expected.put("VBM", 72434L);
        expected.put("SVF", 64415L);
        expected.put("EOF", 73028L);
        assertEquals(expected, calculator.countLapTime());
    }
}
