package src.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PhoneBatteryCapacity {
    SMALL(2000),
    MEDIUM(3000),
    LARGE(4000);

    private final int capacity;
}
