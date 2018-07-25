package pl.vig.droolshyperon.service;

public interface ApplicationService<T> {

    int service(T obj, String... args);

}
