package com.arago.impressiontracker.controller;

import com.arago.impressiontracker.service.AdImpressionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ad-impressions")
public class AdImpressionController {

    private final AdImpressionService adImpressionService;

    @Autowired
    public AdImpressionController(final AdImpressionService adImpressionService) {
        this.adImpressionService = adImpressionService;
    }

    @PostMapping("/{adId}")
    public ResponseEntity<Void> trackAd(@PathVariable("adId") final String adId) {

        adImpressionService.trackImpression(adId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
