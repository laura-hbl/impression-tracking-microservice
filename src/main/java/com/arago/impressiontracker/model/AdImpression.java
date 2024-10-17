package com.arago.impressiontracker.model;

import java.util.Objects;

public class AdImpression {

    private String id;
    private String adId;
    private long impressionCount;

    public AdImpression(String adId, long impressionCount) {
        this.adId = adId;
        this.impressionCount = impressionCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdId() {
        return adId;
    }

    public void setAdId(String adId) {
        this.adId = adId;
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
        return impressionCount == that.impressionCount && Objects.equals(id, that.id) && Objects.equals(adId, that.adId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, adId, impressionCount);
    }
}
