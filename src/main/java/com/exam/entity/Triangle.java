package com.exam.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Triangle {
    int id;
    private int a;
    private int b;
    private int c;
}
