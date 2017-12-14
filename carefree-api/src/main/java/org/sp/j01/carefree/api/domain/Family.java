package org.sp.j01.carefree.api.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Family extends org.sp.j01.carefree.Family {

    private Long familyId;
    private LocalDateTime createdAt;

    /**
     *
     * @param familyId
     * @param familyName
     * @param createdAt
     * @param thumbnailPhotoUrl
     * @param standardPhotoUrl
     * @param description
     * @param leavesBehind
     * @param incarceratedDate
     */
    public Family(Long familyId, String familyName, LocalDateTime createdAt, String thumbnailPhotoUrl, String standardPhotoUrl, String description, Integer leavesBehind, LocalDate incarceratedDate) {
        super(familyName, thumbnailPhotoUrl, standardPhotoUrl, description, leavesBehind, incarceratedDate);
        this.familyId = familyId;
        this.createdAt = createdAt;
    }

    /**
     * Constructor for fresh saves, does not contain db generated fields.
     * @param familyName
     * @param thumbnailPhotoUrl
     * @param standardPhotoUrl
     * @param description
     * @param leavesBehind
     * @param incarceratedDate
     */
    public Family(String familyName, String thumbnailPhotoUrl, String standardPhotoUrl, String description, Integer leavesBehind, LocalDate incarceratedDate) {
        super(familyName, thumbnailPhotoUrl, standardPhotoUrl, description, leavesBehind, incarceratedDate);
    }

    public Long getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Long familyId) {
        this.familyId = familyId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
