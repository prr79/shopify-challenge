package com.prr.warehouse.entities;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Warehouse {
    @NonNull
    private Integer id;
    @NonNull
    private String name;
    private String location;
    private boolean hidden;
    private String deletionComments;

    private static Integer index = 0;

    public static Integer getNextIndex() {
        index++;
        return index;
    }
}
