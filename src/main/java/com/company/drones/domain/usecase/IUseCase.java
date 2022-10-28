package com.company.drones.domain.usecase;

public interface IUseCase<T, I> {

    T execute(final I input);
}
