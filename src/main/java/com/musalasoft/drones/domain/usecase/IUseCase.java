package com.musalasoft.drones.domain.usecase;

public interface IUseCase<T, I> {

    T execute(final I input);
}
