import java.math.BigDecimal;
import java.util.Optional;

public class Test {
    public static void main(String[] args) throws Exception {
        BigDecimal a = null;
        Optional<BigDecimal> opt = Optional.ofNullable(a);
        System.out.println(opt.orElseGet(null));
    }
}
