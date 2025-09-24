package ec.gob.sri.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ComprobanteEvento(
        String id,
        String rucEmisor,
        String tipo,
        String establecimiento,
        String puntoEmision,
        String secuencial,
        LocalDate fechaEmision,
        BigDecimal total,
        String claveAcceso
) {}
