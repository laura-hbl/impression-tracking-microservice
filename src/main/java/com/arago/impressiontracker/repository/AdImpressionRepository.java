package com.arago.impressiontracker.repository;

import com.arago.impressiontracker.model.AdImpression;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdImpressionRepository extends CrudRepository<AdImpression, String> {
}
