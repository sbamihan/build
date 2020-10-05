package ph.com.apdu.eurekaserver;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EurekaServerApplicationTests {

	@Test
	public void testsVoid() {
		Assert.state(true, "The tests for this service are turned off for this example.");
	}

}
