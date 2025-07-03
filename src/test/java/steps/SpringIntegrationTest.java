package steps;


import com.airmiles.collectoreventsproducer.test.config.HandlerConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;


@SpringBootTest
@ActiveProfiles("uat")
@ContextConfiguration(classes = {HandlerConfiguration.class})
public class SpringIntegrationTest
{


}
