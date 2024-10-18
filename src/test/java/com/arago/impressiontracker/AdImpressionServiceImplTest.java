package com.arago.impressiontracker;

import com.arago.impressiontracker.model.AdImpression;
import com.arago.impressiontracker.repository.AdImpressionRepository;
import com.arago.impressiontracker.service.AdImpressionServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AdImpressionServiceImplTest {

    @InjectMocks
    private AdImpressionServiceImpl adImpressionService;

    @Mock
    private AdImpressionRepository adImpressionRepository;

    @Test
    public void givenAnAdId_whenTrackImpressionAndNoAdFound_thenSaveNewAdImpression() {
        String adId = "30af5efc-1dd8-4992-b7bd-10518072830";

        adImpressionService.trackAdImpression(adId);

        AdImpression expectedAdImpression = new AdImpression("30af5efc-1dd8-4992-b7bd-10518072830", 1);
        verify(adImpressionRepository).save(expectedAdImpression);
    }

    @Test
    public void givenAnAdId_whenTrackAdImpressionAndAdFound_thenIncrementAndSave() {
        String adId = "30af5efc-1dd8-4992-b7bd-10518072830";
        AdImpression adImpression = new AdImpression(adId, 1);
        when(adImpressionRepository.findById(adId)).thenReturn(Optional.of(adImpression));

        adImpressionService.trackAdImpression(adId);

        AdImpression expectedAdImpression = new AdImpression(adId, 2);
        verify(adImpressionRepository).save(expectedAdImpression);
    }
}
