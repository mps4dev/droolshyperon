package pl.vig.droolshyperon.config;

import org.kie.api.io.Resource;
import org.kie.internal.io.ResourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import pl.vig.droolshyperon.model.Customer;
import pl.vig.droolshyperon.service.ApplicationService;
import pl.vig.droolshyperon.service.ApplicationServiceDroolsImpl;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan("pl.vig.droolshyperon.service")
public class ApplicationConfig {

    @Bean
    public ApplicationService<Customer> droolsService() {
        List<Resource> resources = Customer.getRules()
                .stream()
                .map(ResourceFactory::newClassPathResource)
                .collect(Collectors.toList());
        return new ApplicationServiceDroolsImpl<>(new DroolsBeanFactory().getSessionForResources(resources));
    }
}
