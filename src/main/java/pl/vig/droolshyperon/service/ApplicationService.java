package pl.vig.droolshyperon.service;

import pl.vig.droolshyperon.model.Customer;

public interface ApplicationService<T> {

    void service(T obj);

}
