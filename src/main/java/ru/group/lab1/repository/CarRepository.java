package ru.group.lab1.repository;

import ru.group.lab1.CarOwner;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<CarOwner, Long> {
}
