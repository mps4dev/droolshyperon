package pl.vig.droolshyperon.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.vig.droolshyperon.config.ApplicationConfig;
import pl.vig.droolshyperon.config.LoggingAspect;
import pl.vig.droolshyperon.model.Customer;
import pl.vig.droolshyperon.model.Customer.CustomerType;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class, LoggingAspect.class})
@EnableAspectJAutoProxy
public class CustomerServiceTest {

    @Autowired
    private ApplicationService<Customer> service;

    @Test
    public void giveIndvidualLongStanding_whenFireRule_thenCorrectDiscount() throws Exception {
        Customer customer = new Customer(CustomerType.INDIVIDUAL, 5);
        int ret = this.service.service(customer);
        assertEquals(customer.getDiscount(), 15);
    }

    @Test
    public void giveIndvidualRecent_whenFireRule_thenCorrectDiscount() throws Exception {
        Customer customer = new Customer(CustomerType.INDIVIDUAL, 1);
        int ret = service.service(customer);
        assertEquals(customer.getDiscount(), 5);
    }

    @Test
    public void giveBusinessAny_whenFireRule_thenCorrectDiscount() throws Exception {
        Customer customer = new Customer(CustomerType.BUSINESS, 0);
        int ret = service.service(customer);
        assertEquals(customer.getDiscount(), 20);
    }
}