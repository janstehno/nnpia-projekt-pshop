package cz.upce.fei.nnpia.pshop.service;

import java.util.List;

public interface ServiceI<I> {
    List<I> getAll();

    I getById(Long id);

    I create(I i);

    I update(I i);

    void deleteById(Long id);
}
