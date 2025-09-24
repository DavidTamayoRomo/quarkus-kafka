package ec.gob.sri.api.kafka;

import ec.gob.sri.api.model.ComprobanteValidado;
import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class ComprobanteValidadoDeserializer extends ObjectMapperDeserializer<ComprobanteValidado> {
    public ComprobanteValidadoDeserializer() {
        super(ComprobanteValidado.class);
    }
}
