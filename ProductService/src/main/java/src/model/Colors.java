package src.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Colors {
    BLACK("Black"),
    WHITE("White"),
    BLUE("Blue"),
    RED("Red"),
    GREEN("Green"),
    GOLD("Gold"),
    SILVER("Silver");

    private final String color;
}
