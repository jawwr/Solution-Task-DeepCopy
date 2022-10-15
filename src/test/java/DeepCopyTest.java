import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import testModels.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class DeepCopyTest {
    @Test
    public void testPrimitiveString() {
        String s1 = "abcd";
        Assertions.assertEquals(s1, CopyUtils.deepCopy(s1));
    }

    @Test
    public void testEmptyString() {
        String s1 = "";
        Assertions.assertEquals(s1, CopyUtils.deepCopy(s1));
    }

    @Test
    public void testPrimitiveInteger() {
        int int1 = 123;
        Assertions.assertEquals(int1, CopyUtils.deepCopy(int1));
    }

    @Test
    public void testIntegerClass() {
        Integer int1 = 123;
        Assertions.assertEquals(int1, CopyUtils.deepCopy(int1));
    }

    @Test
    public void testPrimitiveDouble() {
        double double1 = 1.1;
        Assertions.assertEquals(double1, CopyUtils.deepCopy(double1));
    }

    @Test
    public void testDoubleClass() {
        Double double1 = 1.1;
        Assertions.assertEquals(double1, CopyUtils.deepCopy(double1));
    }

    @Test
    public void testCustomClass1() {
        Person person1 = new Person("Vasya", "Pupkin", 23, "Jet Brains");
        Person person2 = CopyUtils.deepCopy(person1);

        Assertions.assertNotSame(person1, person2);

        Assertions.assertEquals(person1.getName(), person2.getName());
        Assertions.assertEquals(person1.getSurname(), person2.getSurname());
        Assertions.assertEquals(person1.getAge(), person2.getAge());
    }

    @Test
    public void testCustomClass2() {
        Point point1 = new Point(2, 3);
        Point point2 = CopyUtils.deepCopy(point1);

        Assertions.assertNotSame(point1, point2);

        Assertions.assertEquals(point1.getX(), point2.getX());
        Assertions.assertEquals(point1.getY(), point2.getY());
    }

    @Test
    public void testCustomClass3() {
        MailMessage mailMessage1 = new MailMessage("Vasya", "Nikita", "Hello, where is my macbook?");
        MailMessage mailMessage2 = CopyUtils.deepCopy(mailMessage1);

        Assertions.assertNotSame(mailMessage1, mailMessage2);

        Assertions.assertEquals(mailMessage1.getFrom(), mailMessage2.getFrom());
        Assertions.assertEquals(mailMessage1.getTo(), mailMessage2.getTo());
        Assertions.assertEquals(mailMessage1.getMessage(), mailMessage2.getMessage());
    }

    @Test
    public void testArrayInteger() {
        int[] ints1 = {1, 2, 3, 4, 5};
        int[] ints2 = CopyUtils.deepCopy(ints1);

        Assertions.assertNotSame(ints1, ints2);
        Assertions.assertArrayEquals(ints1, ints2);
    }

    @Test
    public void testArrayString() {
        String[] str1 = {"a", "b", "c", "d"};
        String[] str2 = CopyUtils.deepCopy(str1);

        Assertions.assertNotSame(str1, str2);
        Assertions.assertArrayEquals(str1, str2);
    }

    @Test
    public void testArrayCustomClass() {
        Point[] points1 = {
                new Point(1, 2),
                new Point(3, 4),
                new Point(5, 6)
        };
        Point[] points2 = CopyUtils.deepCopy(points1);

        Assertions.assertNotSame(points1, points2);
        Assertions.assertEquals(points1[0].getX(), points2[0].getX());
        Assertions.assertEquals(points1[1].getY(), points2[1].getY());
    }

    @Test
    public void testClassWithArrayField() {
        Man man1 = new Man("John", 19, new ArrayList<>(Arrays.asList("holes", "the giver", "ни сы")), new Phone("iPhone 11", "8(800)555-35-35"));
        Man man2 = CopyUtils.deepCopy(man1);

        Assertions.assertNotSame(man1, man2);

        Assertions.assertEquals(man1.getFavoriteBooks().get(0), man2.getFavoriteBooks().get(0));
    }

    @Test
    public void testCollections() {
        List<String> strings1 = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5"));
        List<String> strings2 = CopyUtils.deepCopy(strings1);

        Assertions.assertEquals(strings1, strings2);
        Assertions.assertNotSame(strings1, strings2);
    }

    @Test
    public void testNull() {
        String str1 = null;
        String str2 = CopyUtils.deepCopy(str1);

        Assertions.assertEquals(str1, str2);
    }

    @Test
    public void testComplexClass() {
        List<Man> men1 = new ArrayList<>(Arrays.asList(
                new Man("John", 19, new ArrayList<>(Arrays.asList("holes", "the giver", "ни сы")), new Phone("iPhone 11", "8(800)555-35-35")),
                new Man("Ivan", 19, new ArrayList<>(Arrays.asList("the giver", "holes", "ни сы")), new Phone("iPhone 11", "8(800)555-35-36")),
                new Man("Denis", 19, new ArrayList<>(Arrays.asList("ни сы", "holes", "the giver")), new Phone("iPhone 11", "8(800)555-35-37"))
        ));
        List<Man> men2 = CopyUtils.deepCopy(men1);

        men2.get(2).getPhone().setModel("iPhone 12");

        Assertions.assertNotSame(men1, men2);

        Assertions.assertEquals(men1.get(0).getFavoriteBooks().get(0), men1.get(0).getFavoriteBooks().get(0));
    }

    @Test
    public void testLong(){
        Long l1 = 1L;
        Long l2 = CopyUtils.deepCopy(l1);

        Assertions.assertNotSame(l1, l2);
        Assertions.assertEquals(l1, l2);
    }
}
