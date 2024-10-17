package com.arago.impressiontracker.service;

import com.arago.impressiontracker.model.AdImpression;
import com.arago.impressiontracker.repository.AdImpressionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdImpressionServiceImpl implements AdImpressionService {

    private final AdImpressionRepository adImpressionRepository;

    @Autowired
    public AdImpressionServiceImpl(AdImpressionRepository adImpressionRepository) {
        this.adImpressionRepository = adImpressionRepository;
    }

    @Override
    public void trackImpression(final String adId) {

        // if adImpression with this ad id exist in db, return the objet, else create a new one
        AdImpression adImpression = adImpressionRepository.findById(adId)
                .orElseGet(() -> new AdImpression(adId, 0));

        // Increment the impression count
        adImpression.setImpressionCount(adImpression.getImpressionCount() + 1);

        adImpressionRepository.save(adImpression);
    }
}
