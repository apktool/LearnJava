package com.chapter10;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;

@Getter
@AllArgsConstructor
public class Car {
    private Optional<Insurance> insurance;
}
