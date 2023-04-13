package org.webler.zsolt;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Streams {


    @Test
    void createStreamFromArray() {
        Employee[] arrayOfEmployees = {
                new Employee(1, "Jeff Bezos", 100000),
                new Employee(2, "Bill Gates", 200000),
                new Employee(3, "Mark Zuckerberg", 300000)
        };

        Stream<Employee> employeeStream = Stream.of(arrayOfEmployees);
    }

    @Test
    void createStreamFromList() {
        List<Employee> listOfEmployees = Arrays.asList(
                new Employee(1, "Jeff Bezos", 100000),
                new Employee(2, "Bill Gates", 200000),
                new Employee(3, "Mark Zuckerberg", 300000)
        );

        Stream<Employee> employeeStream = listOfEmployees.stream();
    }

    @Test
    void collectDemo() {
        List<Employee> listOfEmployees = Arrays.asList(
                new Employee(1, "Jeff Bezos", 100000),
                new Employee(2, "Bill Gates", 200000),
                new Employee(3, "Mark Zuckerberg", 300000)
        );

        List<Employee> employees = listOfEmployees.stream().collect(Collectors.toList());

        assertEquals(listOfEmployees, employees);
    }

    @Test
    void forEachDemo() {
        List<Employee> listOfEmployees = Arrays.asList(
                new Employee(1, "Jeff Bezos", 100000),
                new Employee(2, "Bill Gates", 200000),
                new Employee(3, "Mark Zuckerberg", 300000)
        );

        listOfEmployees.stream().forEach(employee -> employee.increaseSalary(10000));

        assertThat(listOfEmployees, contains(
                hasProperty("salary", equalTo(110000)),
                hasProperty("salary", equalTo(210000)),
                hasProperty("salary", equalTo(310000))
        ));
    }

    @Test
    void mapDemo() {
        List<Employee> listOfEmployees = Arrays.asList(
                new Employee(1, "Jeff Bezos", 100000),
                new Employee(2, "Bill Gates", 200000),
                new Employee(3, "Mark Zuckerberg", 300000)
        );
        List<Integer> ids = Arrays.asList(1, 2, 3);

        List<Integer> collectedIds = listOfEmployees.stream().map(Employee::getId).collect(Collectors.toList());

        assertEquals(ids, collectedIds);
    }

    @Test
    void filterDemo() {
        List<Employee> listOfEmployees = Arrays.asList(
                new Employee(1, "Jeff Bezos", 100000),
                new Employee(2, "Bill Gates", 200000),
                new Employee(3, "Mark Zuckerberg", 300000)
        );

        List<Employee> employees = listOfEmployees.stream()
                .filter(employee -> employee.getSalary() > 100000)
                .collect(Collectors.toList());

        assertEquals(
                Arrays.asList(listOfEmployees.get(1), listOfEmployees.get(2)),
                employees);
    }

    @Test
    void findFirstDemo() {
        List<Employee> listOfEmployees = Arrays.asList(
                new Employee(1, "Jeff Bezos", 100000),
                new Employee(2, "Bill Gates", 200000),
                new Employee(3, "Mark Zuckerberg", 300000)
        );

        Optional<Employee> employee = listOfEmployees.stream().findFirst();

        assertTrue(employee.isPresent());
        assertEquals(listOfEmployees.get(0), employee.get());
    }

    @Test
    void flatMapDemo() {

        List<Employee> listOfEmployees = Arrays.asList(
                new Employee(1, "Jeff Bezos", 100000),
                new Employee(2, "Bill Gates", 200000),
                new Employee(3, "Mark Zuckerberg", 300000)
        );

        Map<String, List<Employee>> employeesByOrganization = new HashMap<>();

        employeesByOrganization.put("amazonEmployees", Arrays.asList(listOfEmployees.get(0)));
        employeesByOrganization.put("microsoftEmployees", Arrays.asList(listOfEmployees.get(1)));
        employeesByOrganization.put("facebookEmployees", Arrays.asList(listOfEmployees.get(2)));

        List<Employee> employees = employeesByOrganization.entrySet().stream()
                .flatMap(entry -> entry.getValue().stream())
                .collect(Collectors.toList());


        assertTrue(listOfEmployees.containsAll(employees));
    }

    @Test
    void peekDemo() {
        List<Employee> listOfEmployees = Arrays.asList(
                new Employee(1, "Jeff Bezos", 100000),
                new Employee(2, "Bill Gates", 200000),
                new Employee(3, "Mark Zuckerberg", 300000)
        );

        listOfEmployees.stream()
                .peek(employee -> employee.increaseSalary(10000))
                .peek(System.out::println)
                .collect(Collectors.toList());

        assertThat(listOfEmployees, contains(
                hasProperty("salary", equalTo(110000)),
                hasProperty("salary", equalTo(210000)),
                hasProperty("salary", equalTo(310000))
        ));
    }

    @Test
    void countDemo() {
        List<Employee> listOfEmployees = Arrays.asList(
                new Employee(1, "Jeff Bezos", 100000),
                new Employee(2, "Bill Gates", 200000),
                new Employee(3, "Mark Zuckerberg", 300000)
        );

        long count = listOfEmployees.stream().filter(employee -> employee.getSalary() > 200000).count();
        assertEquals(1, count);
    }

    @Test
    void infinityStreamDemo() {
        Stream<Integer> infiniteStream = Stream.iterate(2, i -> i * 2);

        List<Integer> collect = infiniteStream
                .skip(3)
                .limit(5)
                .collect(Collectors.toList());

        assertEquals(collect, Arrays.asList(16, 32, 64, 128, 256));

    }



    @Test
    void sortedDemo() {
        List<Employee> listOfEmployees = Arrays.asList(
                new Employee(1, "Jeff Bezos", 100000),
                new Employee(2, "Bill Gates", 200000),
                new Employee(3, "Mark Zuckerberg", 300000)
        );

        List<Employee> employees = listOfEmployees.stream()
                .sorted((employee1, employee2) -> employee1.getName().compareTo(employee2.getName()))
                .collect(Collectors.toList());

        assertEquals(employees.get(0).getName(), "Bill Gates");
        assertEquals(employees.get(1).getName(), "Jeff Bezos");
        assertEquals(employees.get(2).getName(), "Mark Zuckerberg");
    }

    @Test
    void minMaxDemo() {
        List<Employee> listOfEmployees = Arrays.asList(
                new Employee(1, "Jeff Bezos", 100000),
                new Employee(2, "Bill Gates", 200000),
                new Employee(3, "Mark Zuckerberg", 300000)
        );

        Employee minSalaryEmployee = listOfEmployees.stream()
                .min((employee1, employee2) -> employee1.getSalary() - employee2.getSalary())
                .orElseThrow(NoSuchElementException::new);

        Employee maxSalaryEmployee = listOfEmployees.stream()
                .max((employee1, employee2) -> employee1.getSalary() - employee2.getSalary())
                .orElseThrow(NoSuchElementException::new);

        assertEquals(minSalaryEmployee, listOfEmployees.get(0));
        assertEquals(maxSalaryEmployee, listOfEmployees.get(2));
    }

    @Test
    void distinctDemo() {
        List<Employee> listOfEmployees = Arrays.asList(
                new Employee(1, "Jeff Bezos", 100000),
                new Employee(1, "Jeff Bezos", 100000),
                new Employee(2, "Bill Gates", 200000),
                new Employee(2, "Bill Gates", 200000),
                new Employee(2, "Bill Gates", 200000),
                new Employee(3, "Mark Zuckerberg", 300000)
        );

        List<Employee> distinctEmployees = listOfEmployees.stream().distinct().collect(Collectors.toList());

        assertEquals(3, distinctEmployees.size());
    }

    @Test
    void matcherDemo() {
        List<Integer> intList = Arrays.asList(2, 4, 5, 6, 8);

        boolean allEven = intList.stream().allMatch(i -> i % 2 == 0);
        boolean oneEven = intList.stream().anyMatch(i -> i % 2 == 0);
        boolean noneMultipleOfThree = intList.stream().noneMatch(i -> i % 3 == 0);

        assertEquals(allEven, false);
        assertEquals(oneEven, true);
        assertEquals(noneMultipleOfThree, false);
    }

    @Test
    void streamCreationDemo() {
        IntStream intStream = IntStream.of(1, 2, 3);

        IntStream intStreamRange = IntStream.range(10, 20);

        Stream<Integer> stream = Stream.of(1, 2, 3);
    }

    @Test
    void specializedOperationsDemo() {
        int sum = IntStream.range(10, 20).sum();
        OptionalDouble average = IntStream.range(10, 20).average();
        IntSummaryStatistics intSummaryStatistics = IntStream.range(10, 20).summaryStatistics();


        assertEquals(145, sum);
        assertEquals(14.5, average.getAsDouble());

        assertEquals(19, intSummaryStatistics.getMax());
        assertEquals(10, intSummaryStatistics.getMin());
        assertEquals(145, intSummaryStatistics.getSum());
        assertEquals(14.5, intSummaryStatistics.getAverage());

    }

    @Test
    void reduceDemo() {
        List<Employee> listOfEmployees = Arrays.asList(
                new Employee(1, "Jeff Bezos", 100000),
                new Employee(2, "Bill Gates", 200000),
                new Employee(3, "Mark Zuckerberg", 300000)
        );

        Integer sumSalary = listOfEmployees.stream()
                .map(Employee::getSalary)
                .reduce(0, Integer::sum);

        assertEquals(sumSalary, 600000);
    }

    @Test
    void joiningDemo() {
        List<Employee> listOfEmployees = Arrays.asList(
                new Employee(1, "Jeff Bezos", 100000),
                new Employee(2, "Bill Gates", 200000),
                new Employee(3, "Mark Zuckerberg", 300000)
        );

        String employeeNames = listOfEmployees.stream()
                .map(Employee::getName)
                .collect(Collectors.joining(", "))
                .toString();

        assertEquals(employeeNames, "Jeff Bezos, Bill Gates, Mark Zuckerberg");
    }

    @Test
    void partitioningDemo() {
        List<Integer> intList = Arrays.asList(2, 4, 5, 6, 8);
        Map<Boolean, List<Integer>> isEven = intList.stream().collect(
                Collectors.partitioningBy(i -> i % 2 == 0));

        assertEquals(isEven.get(true).size(), 4);
        assertEquals(isEven.get(false).size(), 1);
    }

    @Test
    void groupingByDemo() {
        List<Employee> listOfEmployees = Arrays.asList(
                new Employee(1, "Jeff Bezos", 100000),
                new Employee(2, "Bill Gates", 200000),
                new Employee(3, "Mark Zuckerberg", 300000)
        );

        Map<Character, List<Employee>> groupByAlphabet = listOfEmployees.stream().collect(
                Collectors.groupingBy(employee -> new Character(employee.getName().charAt(0))));


        assertEquals(groupByAlphabet.get('B').get(0).getName(), "Bill Gates");
        assertEquals(groupByAlphabet.get('J').get(0).getName(), "Jeff Bezos");
        assertEquals(groupByAlphabet.get('M').get(0).getName(), "Mark Zuckerberg");
    }


}
