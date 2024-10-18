package com.arago.impressiontracker.service;

import com.arago.impressiontracker.model.AdImpression;
import com.arago.impressiontracker.repository.AdImpressionRepository;
import com.arago.tracking.TrackAdImpressionRequest;
import com.arago.tracking.TrackAdImpressionResponse;
import com.arago.tracking.TrackingServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class AdImpressionServiceImpl extends TrackingServiceGrpc.TrackingServiceImplBase implements AdImpressionService {

    private final AdImpressionRepository adImpressionRepository;

    @Autowired
    public AdImpressionServiceImpl(AdImpressionRepository adImpressionRepository) {
        this.adImpressionRepository = adImpressionRepository;
    }

    @Override
    public void trackAdImpression(TrackAdImpressionRequest request, StreamObserver<TrackAdImpressionResponse> responseObserver) {
        String adId = request.getAdId();
        long count = trackAdImpression(adId);

        responseObserver.onNext(TrackAdImpressionResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Incremented impression count for adId: %s with count %d".formatted(adId, count))
                .build());
        responseObserver.onCompleted();
    }

    @Override
    public Long trackAdImpression(final String adId) {

        // if adImpression with this ad id exist in db, return the objet, else create a new one
        AdImpression adImpression = adImpressionRepository.findById(adId)
                .orElseGet(() -> new AdImpression(adId, 0));

        // Increment the impression count
        long impressionCount = adImpression.getImpressionCount() + 1;
        adImpression.setImpressionCount(impressionCount);

        adImpressionRepository.save(adImpression);
        return impressionCount;
    }
}
