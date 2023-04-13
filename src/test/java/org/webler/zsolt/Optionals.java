package org.webler.zsolt;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class Optionals {

    @Test
    void beforeOptional() {

        User user = new User();

        if (user != null) {
            Address address = user.getAddress();
            if (address != null) {
                Country country = address.getCountry();
                if (country != null) {
                    String isoCode = country.getIsoCode();
                    if (isoCode != null) {
                        System.out.println(isoCode.toUpperCase());
                    }
                }
            }
        }


    }

    @Test
    void createEmptyOptionalInAWrongWayDemo() {
        User user = null;
        Exception exception = assertThrows(NullPointerException.class, () -> {
            Optional<User> emptyOpt = Optional.of(user);
            emptyOpt.get();
        });
    }

    @Test
    void createEmptyOptionalDemo() {
        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            Optional<User> emptyOpt = Optional.empty();
            emptyOpt.get();
        });
    }

    @Test
    void createEmptyOptionalOfNullableDemo() {
        User user = null;
        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            Optional<User> emptyOpt = Optional.ofNullable(user);
            emptyOpt.get();
        });
    }

    @Test
    void accessingValueDemo() {
        User user = new User();

        Optional<User> optionalUser = Optional.of(user);
        assertEquals(user, optionalUser.get());

    }

    @Test
    void defaultValueDemo() {
        String nullString = null;
        String defaultString = "DEFAULT";

        String result = Optional.ofNullable(nullString).orElse(defaultString);
        assertEquals(result, defaultString);

    }

    User createNewUser() {
        System.out.println("New User is created!");
        return new User();
    }

    @Test
    void orElseVsOrElseGetDemo() {
        User user = new User("John", null);
        System.out.println("Call orElse()");
        User result = Optional.ofNullable(user).orElse(createNewUser());
        System.out.println("Call orElseGet()");
        User result2 = Optional.ofNullable(user).orElseGet(() -> createNewUser());
    }

    class MyException extends Exception {
        public MyException(String message) {
            super(message);
        }
    }

    @Test
    void throwExceptionDemo() {
        Optional<User> emptyOpt = Optional.empty();
        String myExceptionMessage = "MY_EXCEPTION";
        Exception exception = assertThrows(MyException.class, () -> {

            emptyOpt.orElseThrow(() -> new MyException(myExceptionMessage));

        });

        assertTrue(exception instanceof MyException);
        assertEquals(myExceptionMessage, exception.getMessage());
    }

    @Test
    void optionalDemo() {
        User user = new User("John", new Address(new Country(null, null), "Street", "65"));
        String result = Optional.ofNullable(user)
                .flatMap(u -> Optional.ofNullable(u.getAddress()))
                .flatMap(u -> Optional.ofNullable(u.getCountry()))
                .map(u -> u.getIsoCode())
                .orElse("default");

        assertEquals(result,"default");
    }


}
