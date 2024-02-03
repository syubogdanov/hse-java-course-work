package test.java.ru.hse.edu;

import java.time.LocalDate;
import java.util.Objects;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import main.java.ru.hse.edu.AlmaMater;
import main.java.ru.hse.edu.Student;

public class StudentTest {
    public Student getRandomStudent() {
        return new Student("Java", "Java", LocalDate.parse("2020-12-21"));
    }

    @Test
    public void testEmail() {
        var student = this.getRandomStudent();

        student.clearEmail();
        Assertions.assertFalse(student.hasEmail());

        var email = "ivan@ivanov.com";
        student.setEmail(email);

        Assertions.assertTrue(student.hasEmail());
        Assertions.assertEquals(email, student.getEmail());

        var other = this.getRandomStudent();
        other.setEmail(email);

        Assertions.assertEquals(other.getEmail(), student.getEmail());

        Assertions.assertThrows(
            NullPointerException.class,
            () -> student.setEmail(null)
        );

        Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> student.setEmail("www.ivan.ru")
        );
    }

    @Test
    public void testPhone() {
        var student = this.getRandomStudent();

        student.clearPhone();
        Assertions.assertFalse(student.hasPhone());

        var phone = "+71112223344";
        student.setPhone(phone);

        Assertions.assertTrue(student.hasPhone());
        Assertions.assertEquals(phone, student.getPhone());

        var other = this.getRandomStudent();
        other.setPhone(phone);

        Assertions.assertEquals(student.getPhone(), other.getPhone());

        Assertions.assertThrows(
            NullPointerException.class,
            () -> student.setPhone(null)
        );

        Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> student.setPhone("Hello, World!")
        );
    }

    @Test
    public void testAlmaMater() {
        var student1 = this.getRandomStudent();
        var student2 = this.getRandomStudent();

        student1.setBirthday(LocalDate.parse("2000-01-01"));
        student2.setBirthday(LocalDate.parse("2000-01-01"));

        student1.clearAlmaMater();
        student2.clearAlmaMater();

        Assertions.assertFalse(student1.hasAlmaMater());
        Assertions.assertFalse(student2.hasAlmaMater());

        var almaMater = new AlmaMater("HSE", 2000);
        long year = 2020;

        student1.setAlmaMater(almaMater, year);
        student2.setAlmaMater(almaMater, year);

        Assertions.assertTrue(student1.hasAlmaMater());
        Assertions.assertTrue(student2.hasAlmaMater());

        Assertions.assertTrue(Objects.equals(
            student1.getAlmaMater(),
            student2.getAlmaMater())
        );

        Assertions.assertTrue(Objects.equals(
            student1.getGraduationYear(),
            student2.getGraduationYear())
        );
    }

    @Test
    public void testEquality() {
        var student1 = new Student("Ivan", "Ivanov", LocalDate.parse("2000-01-01"));
        var student2 = new Student("Ivan", "Ivanov", LocalDate.parse("2000-01-01"));

        Assertions.assertTrue(student1.equals(student2));
        Assertions.assertTrue(student2.equals(student1));

        student1.setEmail("ivanov@ivan.com");
        student2.setEmail("ivan@ivanov.com");

        Assertions.assertFalse(student1.equals(student2));
        Assertions.assertFalse(student2.equals(student1));

        student1.setEmail("ivan@ivanov.com");

        Assertions.assertTrue(student1.equals(student2));
        Assertions.assertTrue(student2.equals(student1));

        student1.setPhone("+71112223344");
        student2.setPhone("+81112223344");

        Assertions.assertFalse(student1.equals(student2));
        Assertions.assertFalse(student2.equals(student1));

        student2.setPhone("+71112223344");

        Assertions.assertTrue(student1.equals(student2));
        Assertions.assertTrue(student2.equals(student1));

        var hse = new AlmaMater("HSE", 2000);
        var msu = new AlmaMater("MSU", 2000);

        student1.setAlmaMater(hse, 2020);
        student2.setAlmaMater(msu, 2020);

        Assertions.assertFalse(student1.equals(student2));
        Assertions.assertFalse(student2.equals(student1));

        student2.setAlmaMater(hse, 2020);

        Assertions.assertTrue(student1.equals(student2));
        Assertions.assertTrue(student2.equals(student1));
    }
}
