package pbd.ponto_eletronico.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record AdjustmentDetails(String register_1, String register_2, String register_3, String register_4,
                                String startDate, String endDate) {}
