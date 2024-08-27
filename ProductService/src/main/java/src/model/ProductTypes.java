package src.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProductTypes {
    COMPUTER("computer"),
    SMARTPHONE("smartphone"),
    ELECTRONICS("electronics");

    private final String type;
}
