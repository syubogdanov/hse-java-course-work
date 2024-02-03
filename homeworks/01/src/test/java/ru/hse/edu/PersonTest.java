package test.java.ru.hse.edu;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import main.java.ru.hse.edu.Person;

public class PersonTest {
    public Person getRandomPerson() {
        return new Person("Java", "Java", LocalDate.parse("2020-12-21"));
    }

    @Test
    public void testName() {
        var person = this.getRandomPerson();

        String[] valid_names = {
            "Akhmed",
            "Igor",
            "Juan Li",
            "Oleg",
            "Vladimir",
            "Vitaliy"
        };

        for (var name : valid_names) {
            person.setName(name);
            Assertions.assertEquals(name, person.getName());
        }

        Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> person.setName(null)
        );

        String[] invalid_names = {
            "Hello, World",
            "I have 0 digits",
            "What?!"
        };

        for (var name : invalid_names) {
            Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> person.setName(name)
            );
        }
    }

    @Test
    public void testValidSurname() {
        var person = this.getRandomPerson();

        String[] valid_surnames = {
            "Akhmedov",
            "Ignatiev",
            "Loo-Wee",
            "Omarov",
            "Valiev",
            "Vladimirov"
        };

        for (var surname : valid_surnames) {
            person.setSurname(surname);
            Assertions.assertEquals(surname, person.getSurname());
        }

        Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> person.setSurname(null)
        );

        String[] invalid_surnames = {
            "Good Bye, World",
            "Call 911!",
            "<- = Smile = ->"
        };

        for (var surname : invalid_surnames) {
            Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> person.setSurname(surname)
            );
        }
    }

    @Test
    public void testBirthday() {
        var person = this.getRandomPerson();

        LocalDate[] valid_dates = {
            LocalDate.parse("2023-01-23"),
            LocalDate.parse("1999-02-16"),
            LocalDate.parse("1962-02-02"),
            LocalDate.parse("1988-05-07"),
            LocalDate.parse("2001-11-29"),
        };

        for (var date : valid_dates) {
            person.setBirthday(date);
            Assertions.assertEquals(date, person.getBirthday());
        }

        Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> person.setBirthday(null)
        );

        LocalDate[] invalid_dates = {
            LocalDate.parse("2828-01-01"),
            LocalDate.parse("3000-01-01"),
            LocalDate.parse("9182-01-01"),
            LocalDate.parse("4321-01-01"),
            LocalDate.parse("7777-01-01"),
        };

        for (var date : invalid_dates) {
            Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> person.setBirthday(date)
            );
        }
    }

    @Test
    public void testAgeGetter() {
        var person = this.getRandomPerson();

        LocalDate[] dates = {
            LocalDate.parse("2023-01-23"),
            LocalDate.parse("1999-02-16"),
            LocalDate.parse("1962-02-02"),
            LocalDate.parse("1988-05-07"),
            LocalDate.parse("2001-11-29"),
        };

        for (var date : dates) {
            long expected = date.until(LocalDate.now()).getYears();
            person.setBirthday(date);
            Assertions.assertEquals(expected, person.getAge());
        }
    }

    @Test
    public void testAdultIndicator() {
        var person = this.getRandomPerson();

        LocalDate[] adult_dates = {
            LocalDate.parse("2000-01-23"),
            LocalDate.parse("1975-02-16"),
            LocalDate.parse("1670-02-02"),
            LocalDate.parse("1988-05-07"),
            LocalDate.parse("2005-01-01"),
        };

        for (var date : adult_dates) {
            person.setBirthday(date);
            Assertions.assertTrue(person.isAdult());
            Assertions.assertFalse(person.isUnderage());
        }
    }

    @Test
    public void testEquality() {
        var person1 = new Person("Ivan", "Ivanov", LocalDate.parse("2000-01-01"));
        var person2 = new Person("Ivan", "Ivanov", LocalDate.parse("2000-01-01"));

        Assertions.assertEquals(person1, person2);
        Assertions.assertEquals(person2, person1);

        person2 = new Person("Angelina", "Ivanova", LocalDate.parse("2000-01-01"));

        Assertions.assertNotEquals(person1, person2);
        Assertions.assertNotEquals(person2, person1);

        person2 = new Person("Ivan", "Ivanov", LocalDate.parse("2020-01-01"));

        Assertions.assertNotEquals(person1, person2);
        Assertions.assertNotEquals(person2, person1);
    }
}
