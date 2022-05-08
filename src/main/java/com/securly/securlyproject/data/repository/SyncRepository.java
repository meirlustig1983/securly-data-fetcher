package com.securly.securlyproject.data.repository;

import com.securly.securlyproject.data.model.Sync;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SyncRepository extends CrudRepository<Sync, Long> {
}