package model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ComputerDto extends Product{
    private String processor;
    private RAMs ram;
}
