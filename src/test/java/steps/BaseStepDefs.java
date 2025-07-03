package steps;

import org.springframework.beans.factory.annotation.Value;

public class BaseStepDefs extends SpringIntegrationTest {

    @Value("${app.aws.travel2-finance-ingester-s3-incoming}")protected String s3Incoming;

}
