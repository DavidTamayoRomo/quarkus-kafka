package ec.gob.sri.service;

import ec.gob.sri.model.ComprobanteEvento;
import ec.gob.sri.model.ComprobanteValidado;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
public class ValidadorProcessor {

    @Channel("comprobantesvalidadoout")
    Emitter<ComprobanteValidado> out;

    @Incoming("comprobantesin")
    public void procesar(ComprobanteEvento ev) {
        boolean firmaOk = ev.claveAcceso() != null && ev.claveAcceso().length() >= 10;
        boolean estructuraOk = ev.secuencial() != null && ev.secuencial().matches("\\d{9}");
        String motivo = (firmaOk && estructuraOk) ? "OK"
                : (firmaOk ? "Secuencial inválido"
                : (estructuraOk ? "Clave de acceso corta" : "Firma y estructura inválidas"));

        var validado = new ComprobanteValidado(ev.id(), firmaOk, estructuraOk, motivo);
        out.send(validado);
        System.out.printf("[VALIDADOR] id=%s → %s (firma=%s, estructura=%s)%n",
                ev.id(), motivo, firmaOk, estructuraOk);
    }
}
