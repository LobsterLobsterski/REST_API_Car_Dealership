package com.tomasz.dealership.mappers;

public interface Mapper <A, B>{
    //maps entity classes to dto classes
    //and vice versa
    B mapTo(A a);
    A mapFrom(B b);
}
