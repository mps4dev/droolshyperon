package pl.vig.droolshyperon.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kie.api.runtime.KieSession;

@AllArgsConstructor
@NoArgsConstructor
public class ApplicationServiceDroolsImpl<T> implements ApplicationService<T> {

    @Setter
    private KieSession kieSession;

    public int service(T obj, String... args) {
        kieSession.insert(obj);
        return kieSession.fireAllRules();
    }
}
