//import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

//@Slf4j
@ExtendWith(MockitoExtension.class)
public class TestUnit {
    @Spy
    List<String> spyList = new ArrayList<String>();

    @Test
    void whenUsingTheSpyAnnotation_thenObjectIsSpied() {
        spyList.add("one");
        spyList.add("two");

        verify(spyList).add("one");
        verify(spyList).add("two");

        assertThat(spyList).hasSize(2);

    }
}
