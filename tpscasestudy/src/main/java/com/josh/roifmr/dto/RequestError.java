package com.josh.roifmr.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class RequestError {

    private LocalDateTime occurredAt;
    private int errorCode;
    private String summary;
    private List<String> details;

    public RequestError() {
        this.occurredAt = LocalDateTime.now();
    }

    public RequestError(int errorCode, String summary) {
        this();
        this.errorCode = errorCode;
        this.summary = summary;
    }

    public RequestError(int errorCode, String summary, List<String> details) {
        this(errorCode, summary);
        this.details = details;
    }

    public LocalDateTime getOccurredAt() {
        return occurredAt;
    }

    public void setOccurredAt(LocalDateTime occurredAt) {
        this.occurredAt = occurredAt;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestError that = (RequestError) o;
        return errorCode == that.errorCode &&
                occurredAt.equals(that.occurredAt) &&
                summary.equals(that.summary) &&
                Objects.equals(details, that.details);
    }

    @Override
    public int hashCode() {
        return Objects.hash(occurredAt, errorCode, summary, details);
    }
}
