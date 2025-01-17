package br.com.fincruzado.fincruzado.api;

import java.time.OffsetDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fincruzado.fincruzado.domain.model.Status;
import br.com.fincruzado.fincruzado.domain.repository.StatusRepository;

@RequestMapping("/status")
@RestController
public class StatusController {

    private StatusRepository statusRepository;

    public StatusController(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @GetMapping
    public ResponseEntity<Status> getStatus() {
        Status status = new Status();
        status.setUpdatedAt(OffsetDateTime.now());
        status.setOpenedConnections(this.statusRepository.findDatabaseOpenedConnections("cruzadodb"));
        status.setDatabaseVersion(this.statusRepository.findDatabaseVersion());
        status.setDatabase("Postgres");
        status.setMaxConnections(this.statusRepository.findDatabaseMaxConnections());

        return ResponseEntity.ok(status);
    }

}
