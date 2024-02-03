package main.java.ru.hse.edu;

import java.time.LocalDate;
import java.util.Objects;
import java.util.regex.Pattern;

public class Person {
    private String name;
    private String surname;
    private LocalDate birthday;

    public Person(
        String name,
        String surname,
        LocalDate birthday
    ) {
        this.setName(name);
        this.setSurname(surname);
        this.setBirthday(birthday);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("The argument 'name' must be non-null");
        }
        if (!Pattern.compile("^[A-Z][a-z]*([ -][A-Z][a-z]+)*$").matcher(name).matches()) {
            throw new IllegalArgumentException("The name must consist only of Latin letters");
        }
        this.name = name;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        if (surname == null) {
            throw new IllegalArgumentException("The argument 'surname' must be non-null");
        }
        if (!Pattern.compile("^[A-Z][a-z]*([ -][A-Z][a-z]+)*$").matcher(surname).matches()) {
            throw new IllegalArgumentException("The surname must consist only of Latin letters");
        }
        this.surname = surname;
    }

    public LocalDate getBirthday() {
        return this.birthday;
    }

    public void setBirthday(LocalDate birthday) {
        if (birthday == null) {
            throw new IllegalArgumentException("The argument 'birthday' must be non-null");
        }
        if (birthday.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("The birthday can not be a future date");
        }
        this.birthday = birthday;
    }

    public long getAge() {
        return this.birthday.until(LocalDate.now()).getYears();
    }

    public boolean isAdult() {
        return this.getAge() >= 18;
    }

    public boolean isUnderage() {
        return !this.isAdult();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getName()) * 41 + Objects.hash(this.getSurname());
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null) {
            return false;
        }

        if (this.getClass() != other.getClass()) {
            return false;
        }

        if (this.hashCode() != other.hashCode()) {
            return false;
        }

        var person = (Person) other;
        return Objects.equals(this.getAge(), person.getAge()) &&
               Objects.equals(this.getBirthday(), person.getBirthday()) &&
               Objects.equals(this.getName(), person.getName()) &&
               Objects.equals(this.getSurname(), person.getSurname());
    }
}
