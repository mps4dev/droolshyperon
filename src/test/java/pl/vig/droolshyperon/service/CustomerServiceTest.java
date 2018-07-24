package pl.vig.droolshyperon.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.io.Resource;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.vig.droolshyperon.config.ApplicationConfig;
import pl.vig.droolshyperon.config.DroolsBeanFactory;
import pl.vig.droolshyperon.model.Customer;
import pl.vig.droolshyperon.model.Customer.CustomerType;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class CustomerServiceTest {

    private ApplicationServiceDroolsImpl<Customer> service = new ApplicationServiceDroolsImpl<>();

    private KieSession kieSession;

    private final static DroolsBeanFactory DROOLS_BEAN_FACTORY = new DroolsBeanFactory();

    @Before
    public void setup() {
        List<Resource> resources = xlsRules().stream().map(ResourceFactory::newClassPathResource).collect(Collectors.toList());
        kieSession = DROOLS_BEAN_FACTORY.getSessionForResources(resources);
        service.setKieSession(kieSession);
    }

    @Test
    public void giveIndvidualLongStanding_whenFireRule_thenCorrectDiscount() throws Exception {
        Customer customer = new Customer(CustomerType.INDIVIDUAL, 5);
        service.service(customer);
        assertEquals(customer.getDiscount(), 15);
    }

    @Test
    public void giveIndvidualRecent_whenFireRule_thenCorrectDiscount() throws Exception {
        Customer customer = new Customer(CustomerType.INDIVIDUAL, 1);
        service.service(customer);
        assertEquals(customer.getDiscount(), 5);
    }

    @Test
    public void giveBusinessAny_whenFireRule_thenCorrectDiscount() throws Exception {
        Customer customer = new Customer(CustomerType.BUSINESS, 0);
        service.service(customer);
        assertEquals(customer.getDiscount(), 20);
    }

    private static List<String> xlsRules() {
        return Arrays.asList("Discount.xls");
    }

}