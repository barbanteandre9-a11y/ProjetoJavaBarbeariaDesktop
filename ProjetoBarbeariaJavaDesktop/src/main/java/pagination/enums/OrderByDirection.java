/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package pagination.enums;

/**
 *
 * @author ferna
 */
public enum OrderByDirection {

    DESCENDING("DESC", 1),
    ASCENDING("ASC", 2);

    private final String description;
    private final int value;

    OrderByDirection(String description, int value) {
        this.description = description;
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public int getValue() {
        return value;
    }
}
