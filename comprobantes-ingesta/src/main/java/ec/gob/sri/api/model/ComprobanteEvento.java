package ec.gob.sri.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ComprobanteEvento(
        String id,
        String rucEmisor,
        String tipo,            // FACTURA, NC, ND...
        String establecimiento, // 001
        String puntoEmision,    // 001
        String secuencial,      // 000000123
        LocalDate fechaEmision,
        BigDecimal total,
        String claveAcceso
) {}
