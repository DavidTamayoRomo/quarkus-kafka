package ec.gob.sri.api;

import ec.gob.sri.api.model.ComprobanteEvento;
import ec.gob.sri.api.model.ComprobanteValidado;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@Path("/ingesta/comprobantes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class IngestaResource {

    @Inject
    @Channel("comprobantes-out")
    Emitter<ComprobanteEvento> emitter;

    @GET
    @Path("/_ping")
    public Response ping() {
        return Response.ok("{\"status\":\"ok\"}").build();
    }


    @POST
    public Response publicar(@Valid @NotNull ComprobanteEvento dto) {
        emitter.send(dto);
        return Response.accepted().build(); // 202 Accepted
    }

    // Escucha validaciones del segundo servicio (cuando lo tengamos listo)
    @Incoming("comprobantes-validado-in")
    public void escucharValidado(ComprobanteValidado v) {
        System.out.printf(
            "[INGESTA] Validación id=%s firma=%s estructura=%s motivo=%s%n",
            v.id(), v.firmaValida(), v.estructuraValida(), v.motivo()
        );
    }
}
