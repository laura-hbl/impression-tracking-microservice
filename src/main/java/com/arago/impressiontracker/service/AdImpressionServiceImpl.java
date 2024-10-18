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
        trackAdImpression(request.getAdId());
        responseObserver.onNext(TrackAdImpressionResponse.newBuilder()
                .setSuccess(true)
                .setMessage("incremented")
                .build());
        responseObserver.onCompleted();
    }

    @Override
    public void trackAdImpression(final String adId) {

        // if adImpression with this ad id exist in db, return the objet, else create a new one
        AdImpression adImpression = adImpressionRepository.findById(adId)
                .orElseGet(() -> new AdImpression(adId, 0));

        // Increment the impression count
        adImpression.setImpressionCount(adImpression.getImpressionCount() + 1);

        adImpressionRepository.save(adImpression);
    }
}
