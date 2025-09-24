package ec.gob.sri.kafka;

import ec.gob.sri.model.ComprobanteEvento;
import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class ComprobanteEventoDeserializer extends ObjectMapperDeserializer<ComprobanteEvento> {
    public ComprobanteEventoDeserializer() {
        super(ComprobanteEvento.class);
    }
}
