package model.fridge;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FrigoTest {

    private Frigo frigo;

    @Before
    public void setUp() throws Exception {
        this.frigo = new Frigo(15.0f, 53.0f, 21.0f, 14.0f, 15.0f);
    }

    @Test
    public void dewPoint() {
        assertEquals(this.frigo.dewPoint(), false);
    }
}