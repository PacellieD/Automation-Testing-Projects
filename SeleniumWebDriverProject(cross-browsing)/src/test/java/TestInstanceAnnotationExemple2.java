import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class TestInstanceAnnotationExemple2 {
    private int nbreHeures=5;
    @Test
    void mettreNombreHeuresA8(){
        nbreHeures+=3;
        assertEquals(8, nbreHeures);
    }
    @Test
    void mettreNombreHeuresA9(){
        nbreHeures+=4;
        assertEquals(9, nbreHeures);
    }
}
