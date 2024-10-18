package com.arago.impressiontracker.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.Objects;

/**
 * Ad impression entity.
 */
@RedisHash("adimpression")
public class AdImpression {

    @Id
    private String adId;
    private long impressionCount;

    public AdImpression(String adId, long impressionCount) {
        // We can use the adId as spring will add adimpression to the key when saving
        // adimpression:abcd-efgh etc
        this.adId = adId;
        this.impressionCount = impressionCount;
    }

    public long getImpressionCount() {
        return impressionCount;
    }

    public void setImpressionCount(long impressionCount) {
        this.impressionCount = impressionCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdImpression that = (AdImpression) o;
        return impressionCount == that.impressionCount && Objects.equals(adId, that.adId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adId, adId, impressionCount);
    }
}
