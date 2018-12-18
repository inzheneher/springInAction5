package org.mav.springinaction;

import lombok.Data;

@Data
public class Ingridient {

    private final String id;
    private final String name;
    private final Type type;

    public static enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
