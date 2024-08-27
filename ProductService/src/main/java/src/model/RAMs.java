package src.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RAMs {
    FOUR_GB("4GB"),
    EIGHT_GB("8GB"),
    SIXTEEN_GB("16GB");

    private final String RAM;
}
