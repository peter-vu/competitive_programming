import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.nustaq.serialization.FSTConfiguration;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Notes for serialization size 1 million objects
 * Name/Time/Size
 * Kryo:                148     26000005
 * JDK Serialization:   8836    26000262
 * FST                  320     13000041
 */
public class Test {
    public static void main(String[] args) {
        Long a1 = 1L;
        Long a2 = 1L;
        System.out.println(a1 == a2);

        Long b1 = 1000L;
        Long b2 = 1000L;
        System.out.println(b1 == b2);
    }
    public static void main1(String[] args) throws Exception {
        long timeSum = 0;
        int loop = 100;
        for(int i = 0; i < loop; i++) {
            long time1 = System.nanoTime();
            kryoSerialize();
            long time2 = System.nanoTime();
            timeSum += (time2 - time1);
            System.out.println("Execution time: " + (time2 - time1) / 1_000_000);
        }
        System.out.println("Average time: " + (timeSum/loop)/ 1_000_000);
    }

    static void fstSerializer() throws Exception {
        FSTConfiguration conf = FSTConfiguration.createDefaultConfiguration();
        List<Person> personList = generatePersonList();
        byte[] bytes = conf.asByteArray(personList);
        File f = new File("src/main/java/test.txt");
        FileOutputStream fos = new FileOutputStream(f);
        fos.write(bytes);
        fos.close();

        FileInputStream fis = new FileInputStream(f);
        bytes = fis.readAllBytes();
        personList = (List<Person>) conf.asObject(bytes);
        System.out.println("Deserialization: " + personList.size());
        fis.close();
    }

    static void kryoSerialize() throws Exception {
        Kryo kryo = new Kryo();
        kryo.register(ArrayList.class);
        kryo.register(Person.class);

        List<Person> personList = generatePersonList();

        File f = new File("src/main/java/test.txt");
        Output output = new Output(new FileOutputStream(f));
        kryo.writeObject(output, (List<Person>)personList);
        output.close();

        Input input = new Input(new FileInputStream(f));
        personList = (List<Person>) kryo.readObject(input, ArrayList.class);
        System.out.println("Deserialization: " + personList.size());
        input.close();
    }

    static void jdkSerialize() throws Exception {
        List<Person> personList = generatePersonList();
        File f = new File("src/main/java/test.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(f));
        objectOutputStream.writeObject(personList);
        objectOutputStream.close();

        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(f));
        personList = (List<Person>) objectInputStream.readObject();
        System.out.println("Deserialization: " + personList.size());
        objectInputStream.close();
    }

    static List<Person> generatePersonList() {
        List<Person> personList = new ArrayList<>();
        int count = 1_000_000;

        for(int i = 0; i < count; i++) {
            Person person = new Person("firstName", "lastName", 20, "address");
            personList.add(person);
        }
        return personList;
    }

    static class Person implements Serializable {
        private static final long serialVersionUID  = 9081551316051484686L;
        private String firstName;
        private String lastName;
        private Integer age;
        private String address;

        public Person() {

        }
        public Person(String firstName, String lastName, Integer age, String address) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
            this.address = address;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Person person)) return false;
            return Objects.equals(getFirstName(), person.getFirstName()) && Objects.equals(getLastName(), person.getLastName()) && Objects.equals(getAge(), person.getAge()) && Objects.equals(getAddress(), person.getAddress());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getFirstName(), getLastName(), getAge(), getAddress());
        }

        public String getFirstName() {
            return firstName;
        }

        public Person setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public String getLastName() {
            return lastName;
        }

        public Person setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Integer getAge() {
            return age;
        }

        public Person setAge(Integer age) {
            this.age = age;
            return this;
        }

        public String getAddress() {
            return address;
        }

        public Person setAddress(String address) {
            this.address = address;
            return this;
        }
    }
}
