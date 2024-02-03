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
}
