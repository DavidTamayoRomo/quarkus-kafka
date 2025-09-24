package ec.gob.sri.model;

public record ComprobanteValidado(
        String id,
        boolean firmaValida,
        boolean estructuraValida,
        String motivo
) {}
