package com.musalasoft.drones.domain;

public interface IUseCase<T, I> {

    T execute(final I input);
}
