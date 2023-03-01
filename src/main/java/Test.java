import java.math.BigDecimal;
import java.util.Optional;

public class Test {
    public static void main(String[] args) throws Exception {
        Long a = Long.valueOf(12345);
        Long b = Long.parseLong("12345");
        Long c = Long.valueOf(12345);
        System.out.println(a == b);
        System.out.println(a == c);

        Long e = -129L;
        Long f = -129L;
        System.out.println(e == f);

        Boolean a1 = Boolean.parseBoolean("true");
        Boolean b1 = Boolean.TRUE;
        System.out.println(a1 == b1);
    }
}
