package org.webler.zsolt;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        List<Employee> employees = IntStream.range(0, listOfEmployees.size())
                .filter(n -> n % 3 == 0)
                .mapToObj(listOfEmployees::get).collect(Collectors.toList());
    }


    // Streamek segítségével készíts egy Map<String,Double> adatszerkezetet, amiben minden vállalathoz az ott keresők átlag bére legyen társítva.
    // <Amazon,60000>
    @Test
    void feladat_2() {
        Map<Organization, Double> collect = listOfEmployees.stream()
                .collect(Collectors.groupingBy(Employee::getOrganization, Collectors.averagingDouble(Employee::getSalary)));

    }


    // Streamek segítségével készíts egy fizetések alapján rendezett Employee listát, ha két fizetés azonos a nevük alapján rendezd őket.
    @Test
    void feladat_3() {
        List<Employee> employees = listOfEmployees.stream().sorted((a, b) -> {
            if (Double.compare(a.getSalary(), b.getSalary()) != 0) {
                return Double.compare(a.getSalary(), b.getSalary());
            } else {
                return a.getName().compareTo(b.getName());
            }
        }).collect(Collectors.toList());
    }

    //Streamek segítségével készíts egy listát, az amerikai székhelyű Organizationökről. Minden Organization csak egyszer szerepeljen.
    @Test
    void feladat_4() {
        List<Organization> organizations = listOfEmployees.stream()
                .map(Employee::getOrganization)
                .filter(organization -> organization.getCountry().getIsoCode().equals("US"))
                .distinct()
                .collect(Collectors.toList());
    }


    //Készíts egy Map<Organization,Long> adatszerkezetetet, amiben minden organization objektumhoz hozzá rendeled az ott dolgozók számát.
    @Test
    void feladat_5() {
        Map<Organization, Long> collect = listOfEmployees.stream()
                .collect(Collectors.groupingBy(Employee::getOrganization, Collectors.counting()));
    }

    //Készíts egy Map<Organization,List<Employee>> adatszerkezetetet, amiben minden organization objektumhoz hozzá rendeled az ott dolgozókat.
    @Test
    void feladat_6() {
        Map<Organization, List<Employee>> collect = listOfEmployees.stream()
                .collect(Collectors.groupingBy(Employee::getOrganization, Collectors.toList()));
    }

    //Streamek segítségével add meg az átlag felett kereső alkalmazottak listáját.
    @Test
    void feladat_7() {
        List<Employee> employees = listOfEmployees.stream()
                .filter(employee -> employee.getSalary() > 0)
                .collect(Collectors.toList());


    }

    //Streamek segítségével írd ki minden dolgozó nevét, majd töröld ki a vezetéknevüket és írd ki a nevüket újra.
    @Test
    void feladat_8() {
        listOfEmployees.stream()
                .peek(employee -> System.out.println(employee.getName()))
                .peek(employee -> employee.setName(employee.getName().split(" ")[0]))
                .forEach(employee -> System.out.println(employee.getName()));
    }

    //Streamek segítségével növeld meg minden dolgozó fizetését, az adott cégnél dolgozó emberek fizetésének átlagának 10%-val.
    @Test
    void feladat_9() {
        listOfEmployees.stream().forEach(employee -> {
            employee.setSalary((int) (employee.getSalary() + ((int) listOfEmployees.stream().collect(Collectors.groupingBy(Employee::getOrganization, Collectors.averagingDouble(Employee::getSalary))).get(employee.getOrganization()).doubleValue() * 1.0)));
        });
    }

    //Streamek segítségével keresd meg a leghosszabb nevű dolgozót
    @Test
    void feladat_10() {
        listOfEmployees.stream().max(Comparator.comparingInt(a -> a.getName().length()));
    }

    //Streamek segítségével nézd meg, van-e olyan cég, ahol legalább 4-en dolgoznak. A stream térjen vissza egy boolean értékkel.
    @Test
    void feladat_11() {
        boolean b = listOfEmployees.stream().collect(
                        Collectors.groupingBy(Employee::getOrganization, Collectors.counting()))
                .entrySet()
                .stream()
                .anyMatch(entry -> entry.getValue() > 3);
    }

}
