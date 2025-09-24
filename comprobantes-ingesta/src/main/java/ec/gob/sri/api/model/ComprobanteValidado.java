package ec.gob.sri.api.model;

public record ComprobanteValidado(
        String id,
        boolean firmaValida,
        boolean estructuraValida,
        String motivo
) {}
