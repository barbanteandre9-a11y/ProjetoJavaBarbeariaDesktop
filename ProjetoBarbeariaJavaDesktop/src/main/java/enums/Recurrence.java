package enums;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ferna
 */
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@interface Description {
    String value();
}

public enum Recurrence {
    @Description("Despesa única")
    Single(1),
    @Description("Despesa parcelada")
    Installments(2);

    private final int value;

    Recurrence(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}