package com.prr.warehouse.entities;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inventory {
    private Integer id;
    @NonNull
    private String name;
    @NonNull
    private Double cost;
    private String description;
    private boolean hidden;
    private String deletionComments;

    private static Integer index = 0;

    public static Integer getNextIndex() {
        index++;
        return index;
    }
}
