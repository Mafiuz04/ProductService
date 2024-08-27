package src.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Accessories {
    CASE("Protective Case"),
    SCREEN_PROTECTOR("Screen Protector"),
    CHARGER("Charger"),
    HEADPHONES("Headphones"),
    WIRELESS_CHARGER("Wireless Charger"),
    POWER_BANK("Power Bank");

    private final String accessory;
}
