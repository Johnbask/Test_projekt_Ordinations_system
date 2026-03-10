import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import ordination.Ordination;
import ordination.PN;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class PNgivDosisTest {

    @Test
    void test_PN_GivDosis() {
        Ordination PNOrdination = new PN(LocalDate.of(2026,3,9), LocalDate.of(2026,7,15),10);


    }
}
