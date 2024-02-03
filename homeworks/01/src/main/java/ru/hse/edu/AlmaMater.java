package main.java.ru.hse.edu;

import java.time.LocalDate;
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
            throw new IllegalArgumentException("The argument 'name' must be non-null");
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
            throw new IllegalArgumentException("The argument 'license' must be non-null");
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
}
