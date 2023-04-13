package org.webler.zsolt;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class Feladatok {

    private List<Employee> listOfEmployees;

    @BeforeEach
    void init() {
        listOfEmployees = Arrays.asList(
                new Employee(1, "Jeff Bezos", 100000, new Organization("Amazon", new Country("USA", "US"))),
                new Employee(2, "John Smith", 20000, new Organization("Amazon", new Country("USA", "US"))),
                new Employee(3, "Bill Gates", 200000, new Organization("Microsoft", new Country("USA", "US"))),
                new Employee(4, "Paul Allen", 200000, new Organization("Microsoft", new Country("USA", "US"))),
                new Employee(5, "Mark Zuckerberg", 300000, new Organization("Facebook", new Country("Ireland", "IR"))),
                new Employee(6, "Bill Bow", 10000, new Organization("Facebook", new Country("Ireland", "IR"))),
                new Employee(7, "Elon Musk", 300000, new Organization("Twitter", new Country("USA", "US"))),
                new Employee(8, "Parag Agrawal", 300000, new Organization("Twitter", new Country("USA", "US"))),
                new Employee(9, "Tim Cook", 250000, new Organization("Apple", new Country("United Kingdom", "UK"))),
                new Employee(10, "Jony Ive", 50000, new Organization("Apple", new Country("United Kingdom", "UK"))),
                new Employee(11, "Steve Wozniak", 150000, new Organization("Apple", new Country("United Kingdom", "UK")))
        );
    }

    // Streamek segítségével készíts egy listát, a listOfEmployees lista, minden harmadik elemét tartalmazza. [Jeff Bezos, Paul Allen, ...]
    @Test
    void feladat_1() {

    }


    // Streamek segítségével készíts egy Map<String,Integer> adatszerkezetet, amiben minden vállalathoz az ott keresők átlag bére legyen társítva.
    // <Amazon,60000>
    @Test
    void feladat_2() {

    }


    // Streamek segítségével készíts egy fizetések alapján rendezett Employee listát, ha két fizetés azonos a nevük alapján rendezd őket.
    @Test
    void feladat_3() {

    }

    //Streamek segítségével készíts egy listát, az amerikai székhelyű Organizationökről. Minden Organization csak egyszer szerepeljen.
    @Test
    void feladat_4() {

    }


    //Készíts egy Map<Organization,Long> adatszerkezetetet, amiben minden organization objektumhoz hozzá rendeled az ott dolgozók számát.
    @Test
    void feladat_5() {

    }

    //Készíts egy Map<Organization,List<Employee>> adatszerkezetetet, amiben minden organization objektumhoz hozzá rendeled az ott dolgozókat.
    @Test
    void feladat_6() {

    }

    //Streamek segítségével add meg az átlag felett kereső alkalmazottak listáját.
    @Test
    void feladat_7() {

    }

    //Streamek segítségével írd ki minden dolgozó nevét, majd töröld ki a vezetéknevüket és írd ki a nevüket újra.
    @Test
    void feladat_8() {

    }

    //Streamek segítségével növeld meg minden dolgozó fizetését, az adott cégnél dolgozó emberek fizetésének átlagának 10%-val.
    @Test
    void feladat_9() {

    }

    //Streamek segítségével keresd meg a leghosszabb nevű dolgozót
    @Test
    void feladat_10() {

    }

    //Streamek segítségével nézd meg, van-e olyan cég, ahol legalább 4-en dolgoznak. A stream térjen vissza egy boolean értékkel.
    @Test
    void feladat_11() {

    }

}
