package com.example.librarycompetition.dto;

import java.util.List;

public record JsonDTO(
    String bookTitle,
    Integer cameraId,
    Double totalDamageRatio,
    List<Double> gridNum
) {
    public static record GridDamage(Double gridDamageRatio) {}
}
