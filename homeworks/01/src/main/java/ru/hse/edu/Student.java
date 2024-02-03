package main.java.ru.hse.edu;

import java.time.LocalDate;
import java.util.Objects;
import java.util.regex.Pattern;

public class Student extends Person {
    private String email;
    private String phone;

    private AlmaMater almaMater;
    private long graduationYear;

    public Student(
        String name,
        String surname,
        LocalDate birthday
    ) {
        super(name, surname, birthday);

        this.clearEmail();
        this.clearPhone();
        this.clearAlmaMater();
    }

    public boolean hasEmail() {
        return !this.email.isEmpty();
    }

    public String getEmail() {
        if (!this.hasEmail()) {
            throw new RuntimeException("The student does not have an email");
        }
        return this.email;
    }

    public void clearEmail() {
        this.email = "";
    }

    public void setEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException("The argument 'email' must be non-null");
        }
        if (!Pattern.compile("^[^@]+@[^@]+\\.[^@]+$").matcher(email).matches()) {
            throw new IllegalArgumentException("The email does not match the specification");
        }
        this.email = email;
    }

    public boolean hasPhone() {
        return !this.phone.isEmpty();
    }

    public String getPhone() {
        if (!this.hasPhone()) {
            throw new RuntimeException("The student does not have a phone");
        }
        return this.phone;
    }

    public void clearPhone() {
        this.phone = "";
    }

    public void setPhone(String phone) {
        if (phone == null) {
            throw new IllegalArgumentException("The argument 'phone' must be non-null");
        }
        if (!Pattern.compile("^\\+[1-9]{1}[0-9]{3,14}$").matcher(phone).matches()) {
            throw new IllegalArgumentException("The phone does not match the specification");
        }
        this.phone = phone;
    }

    public boolean isGraduated() {
        return this.almaMater != null;
    }

    public boolean hasAlmaMater() {
        return this.isGraduated();
    }

    public AlmaMater getAlmaMater() {
        if (!this.isGraduated()) {
            throw new RuntimeException("The student has not graduated yet");
        }
        return this.almaMater;
    }

    public void setAlmaMater(AlmaMater almaMater, long graduationYear) {
        if (almaMater == null) {
            throw new IllegalArgumentException("The argument 'almaMater' must be non-null");
        }
        if (graduationYear < 0) {
            throw new IllegalArgumentException("The graduation year can not be negative");
        }
        if (graduationYear > LocalDate.now().getYear()) {
            throw new IllegalArgumentException("The graduation year can not be a future date");
        }
        if (LocalDate.now().getYear() - graduationYear > this.getAge()) {
            throw new IllegalArgumentException("The graduation year is older than a person");
        }
        this.almaMater = almaMater;
        this.graduationYear = graduationYear;
    }

    public void clearAlmaMater() {
        this.almaMater = null;
        this.graduationYear = 0;
    }

    public long getGraduationYear() {
        if (!this.isGraduated()) {
            throw new RuntimeException("The student has not graduated yet");
        }
        return this.graduationYear;
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

        if (!super.equals(other)) {
            return false;
        }

        var student = (Student) other;

        boolean equalEmails = !(this.hasEmail() ^ student.hasEmail());
        if (this.hasEmail() && student.hasEmail()) {
            equalEmails = Objects.equals(this.getEmail(), student.getEmail());
        }

        boolean equalPhones = !(this.hasPhone() ^ student.hasPhone());
        if (this.hasPhone() && student.hasPhone()) {
            equalPhones = Objects.equals(this.getPhone(), student.getPhone());
        }

        boolean equalAlmaMaters = !(this.hasAlmaMater() ^ student.hasAlmaMater());
        if (this.hasAlmaMater() && student.hasAlmaMater()) {
            equalAlmaMaters = Objects.equals(this.getAlmaMater(), student.getAlmaMater()) &&
                              Objects.equals(this.getGraduationYear(), student.getGraduationYear());
        }

        return equalEmails && equalPhones && equalAlmaMaters;
    }
}
