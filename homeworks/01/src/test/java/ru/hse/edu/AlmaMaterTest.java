package test.java.ru.hse.edu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import main.java.ru.hse.edu.AlmaMater;

public class AlmaMaterTest {
    public AlmaMater getRandomAlmaMater() {
        return new AlmaMater("HSE", 2000);
    }

    @Test
    public void testName() {
        var almaMater = this.getRandomAlmaMater();

        String[] valid_names = {
            "MSU",
            "MIPT",
            "Stanford University",
            "Cambridge University"
        };

        for (var name : valid_names) {
            almaMater.setName(name);
            Assertions.assertEquals(name, almaMater.getName());
        }

        Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> almaMater.setName(null)
        );

        String[] invalid_names = {
            "Hello?!",
            "Python3.12",
            "_/\\_(*_*)_/\\\\_"
        };

        for (var name : invalid_names) {
            Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> almaMater.setName(name)
            );
        }
    }

    @Test
    public void testLicense() {
        var almaMater = this.getRandomAlmaMater();

        almaMater.clearLicense();
        Assertions.assertFalse(almaMater.hasLicense());

        var license = "RU-0000-0000-0000";
        almaMater.setLicense(license);

        Assertions.assertTrue(almaMater.hasLicense());
        Assertions.assertEquals(license, almaMater.getLicense());

        Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> almaMater.setLicense(null)
        );

        Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> almaMater.setLicense("Yet Another Problem")
        );
    }

    @Test
    public void testEquality() {
        var almaMater1 = new AlmaMater("HSE", 2000);
        var almaMater2 = new AlmaMater("HSE", 2000);

        Assertions.assertEquals(almaMater1, almaMater2);

        Assertions.assertFalse(almaMater1.hasLicense());
        Assertions.assertFalse(almaMater2.hasLicense());

        String license = "RU-0000-0000-0000";

        almaMater1.setLicense(license);
        almaMater2.setLicense(license);

        Assertions.assertEquals(almaMater1, almaMater2);
        Assertions.assertEquals(almaMater2, almaMater1);
    }
}
