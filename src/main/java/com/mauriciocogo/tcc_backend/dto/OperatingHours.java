package com.mauriciocogo.tcc_backend.dto;

import lombok.*;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OperatingHours {
    private Map<String, List<TimeRange>> days;
}
