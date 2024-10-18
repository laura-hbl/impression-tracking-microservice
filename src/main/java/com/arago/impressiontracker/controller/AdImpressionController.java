package com.arago.impressiontracker.controller;

import com.arago.impressiontracker.service.AdImpressionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing ad impressions tracking.
 */
@RestController
@RequestMapping("/ad-impressions")
public class AdImpressionController {

    private final AdImpressionService adImpressionService;

    @Autowired
    public AdImpressionController(final AdImpressionService adImpressionService) {
        this.adImpressionService = adImpressionService;
    }

    /**
     * Tracks an ad impression.
     *
     * @param adId the id of the ad for which to track the impression
     * @return ResponseEntity containing the HTTP status.
     */
    @PostMapping("/{adId}")
    public ResponseEntity<Void> trackAd(@PathVariable("adId") final String adId) {

        adImpressionService.trackAdImpression(adId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
