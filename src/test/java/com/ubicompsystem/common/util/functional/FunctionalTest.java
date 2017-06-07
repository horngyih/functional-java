package com.ubicompsystem.common.util.functional;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

/**
 * Created by horngyih on 6/7/2017.
 */
public class FunctionalTest {
    @SuppressWarnings("EmptyMethod")
    @Before
    public void buildMeUp() {

    }

    @SuppressWarnings("EmptyMethod")
    @After
    public void letMeDown() {

    }

    @Test
    public void testFilter() {
        System.out.println( "Test Filter" );
        List<Person> persons = Arrays.asList(new Person[]{
                new Person("Alex", "Hemmingway", 30, "None"),
                new Person("Ernest", "Hemmingway", 34, "None"),
                new Person("Anne", "Hall", 50, "Apples"),
                new Person("Tom", "Hall", 51, "Oranges"),
                new Person("Lee", "Brady", 14, "Candy"),
                new Person("Cathy", "Brady", 16, "Sandwiches")
        });

        Predicate<Person> under30Filter = new Predicate<Person>() {
            public boolean predicate(Person target) {
                boolean result = false;
                if (target != null) {
                    result = target.getAge() < 30;
                }
                return result;
            }
        };

        Predicate<Person> over40Filter = new Predicate<Person>() {
            public boolean predicate(Person target) {
                boolean result = false;
                if (target != null) {
                    result = target.getAge() > 40;
                }
                return result;
            }
        };

        Predicate<Person> firstNameStartingWithA = new Predicate<Person>() {
            public boolean predicate(Person target) {
                boolean result = false;
                if( target != null ){
                    result = target.getFirstName().toLowerCase().startsWith("a");
                }
                return result;
            }
        };

        under30:
        {
            System.out.println( "- Filtering for persons under 30" );

            //Manual Tally of persons under 30
            int under30Count = 0;
            for( Person p : persons ){
                if( p.getAge() < 30 ){
                    under30Count++;
                }
            }
            Collection<Person> personsUnder30 = Functional.<Person>filter(persons, new ArrayList<Person>(), under30Filter);
            assertNotNull("Should return a non-null filtered list of Persons under 30", personsUnder30);
            assertFalse("Should return a non-empty filered list of Persons under 30", personsUnder30.isEmpty());
            assertEquals( "Should return the expected number of Persons under 30", under30Count, personsUnder30.size() );
            System.out.println( "-- Persons under 30 is " + personsUnder30.size() );
            System.out.println( "-- PASSED" );
        }

        over40:
        {
            System.out.println( "- Filtering for persons over 40" );

            //Manual Tally of persons over 40
            int over40Count = 0;
            for( Person p : persons ){
                if( p.getAge() > 40 ){
                    over40Count++;
                }
            }

            Collection<Person> personsOver40 = Functional.<Person>filter(persons, new LinkedList<Person>(), over40Filter );
            assertNotNull( "Should return a non-null filtered list of Persons over 40", personsOver40 );
            assertFalse( "Should return a non-empty filtered list of Persons over 40", personsOver40.isEmpty() );
            assertEquals( "Should return the expected number of Persons over 40", over40Count, personsOver40.size() );
            System.out.println( "-- Persons over 40 is " + personsOver40.size() );
            System.out.println( "-- PASSED" );
        }

        firstNameStartsWithA:
        {
            System.out.println( "- Filtering for persons with first name starting with 'A'" );

            //Manual tally of persons with first name starting with 'A'
            int aCount = 0;
            for( Person p : persons ){
                if( p.getFirstName().toLowerCase().startsWith("a")){
                    aCount++;
                }
            }

            Collection<Person> personsStartingA = Functional.<Person>filter(persons, new ArrayList<Person>(), firstNameStartingWithA );
            assertNotNull( "Should return a non-null filtered list of Persons with first name starting with 'A'", personsStartingA );
            assertFalse( "Should return a non-empty filtered list of Persons with first name starting with 'A'", personsStartingA.isEmpty() );
            assertEquals( "Should return the expected number of Persons with first name starting with 'A'", aCount, personsStartingA.size() );
            System.out.println( "-- There are " + personsStartingA.size() + " with First Names starting with 'A'" );
            System.out.println( "-- PASSED" );
        }

        System.out.println( "END Test Filter" );
    }

    public static class Person {
        String firstName;
        String lastName;
        int age;
        String favouriteFood;

        public Person(String first, String last, int n, String food) {
            this.firstName = (first != null) ? first : "-";
            this.lastName = (last != null) ? last : "-";
            this.age = (n > 0) ? n : 1;
            this.favouriteFood = (food != null) ? food : "-";
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            firstName = (firstName != null) ? firstName : "-";
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            lastName = (lastName != null) ? lastName : "-";
            this.lastName = lastName;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            age = ( age > 0 )? age : 1;
            this.age = age;
        }

        public String getFavouriteFood() {
            return favouriteFood;
        }

        public void setFavouriteFood(String favouriteFood) {
            favouriteFood = (favouriteFood != null) ? favouriteFood : "-";
            this.favouriteFood = favouriteFood;
        }

        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append(getFirstName());
            builder.append("|");
            builder.append(getLastName());
            builder.append("|");
            builder.append(getAge());
            builder.append("|");
            builder.append(getFirstName());
            return builder.toString();
        }
    }
}

