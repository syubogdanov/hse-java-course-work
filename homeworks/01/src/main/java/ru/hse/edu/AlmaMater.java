package main.java.ru.hse.edu;

import java.time.LocalDate;
import java.util.Objects;
import java.util.regex.Pattern;

public class AlmaMater {
    private String name;
    private long foundationYear;
    private String license;

    public AlmaMater(String name, long foundationYear) {
        if (foundationYear < 0) {
            throw new IllegalArgumentException("The foundation year can not be negative");
        }
        if (foundationYear > LocalDate.now().getYear()) {
            throw new IllegalArgumentException("The foundation year can not be a future date");
        }
        this.setName(name);
        this.foundationYear = foundationYear;
        this.clearLicense();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        if (name == null) {
            throw new NullPointerException("The argument 'name' must be non-null");
        }
        if (!Pattern.compile("^[A-Z]+[a-z]*([ -][A-Z]+[a-z]*)*").matcher(name).matches()) {
            throw new IllegalArgumentException("The name does match the specification");
        }
        this.name = name;
    }

    public long getFoundationYear() {
        return this.foundationYear;
    }

    public void clearLicense() {
        this.license = "";
    }

    public boolean hasLicense() {
        return !this.license.isEmpty();
    }

    public boolean isIllegal() {
        return !this.hasLicense();
    }

    public void setLicense(String license) {
        if (license == null) {
            throw new NullPointerException("The argument 'license' must be non-null");
        }
        if (!Pattern.compile("RU(-[0-9]{4}){3}").matcher(license).matches()) {
            throw new IllegalArgumentException("The license does match the specification");
        }
        this.license = license;
    }

    public String getLicense() {
        if (!this.hasLicense()) {
            throw new IllegalArgumentException("The alma mater does not have a license");
        }
        return this.license;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getName());
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

        var almaMater = (AlmaMater) other;

        boolean equalLicenses = !(this.hasLicense() ^ almaMater.hasLicense());
        if (this.hasLicense() && almaMater.hasLicense()) {
            equalLicenses = Objects.equals(this.getLicense(), almaMater.getLicense());
        }

        return Objects.equals(this.getName(), almaMater.getName()) &&
               Objects.equals(this.getFoundationYear(), almaMater.getFoundationYear()) &&
               equalLicenses;
    }
}
