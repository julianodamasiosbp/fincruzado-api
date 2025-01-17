package br.com.fincruzado.fincruzado.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.fincruzado.fincruzado.domain.model.Status;

public interface StatusRepository extends JpaRepository<Status, Long> {

    @Query(value = "SELECT count(*)::int FROM pg_stat_activity WHERE datname = ?1", nativeQuery = true)
    String findDatabaseOpenedConnections(String database);

    @Query(value = "SHOW server_version;", nativeQuery = true)
    String findDatabaseVersion();

    @Query(value = "SHOW max_connections;", nativeQuery = true)
    String findDatabaseMaxConnections();

}
