package org.sp.j01.carefree;

import java.time.LocalDate;

public class Family {
    private String familyName;
    private String thumbnailPhotoUrl;
    private String standardPhotoUrl;
    private String description;
    private Integer leavesBehind;
    private LocalDate incarceratedDate;

    public Family(String familyName, String thumbnailPhotoUrl, String standardPhotoUrl, String description, Integer leavesBehind, LocalDate incarceratedDate) {
        this.familyName = familyName;
        this.thumbnailPhotoUrl = thumbnailPhotoUrl;
        this.standardPhotoUrl = standardPhotoUrl;
        this.description = description;
        this.leavesBehind = leavesBehind;
        this.incarceratedDate = incarceratedDate;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getThumbnailPhotoUrl() {
        return thumbnailPhotoUrl;
    }

    public void setThumbnailPhotoUrl(String thumbnailPhotoUrl) {
        this.thumbnailPhotoUrl = thumbnailPhotoUrl;
    }

    public String getStandardPhotoUrl() {
        return standardPhotoUrl;
    }

    public void setStandardPhotoUrl(String standardPhotoUrl) {
        this.standardPhotoUrl = standardPhotoUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getLeavesBehind() {
        return leavesBehind;
    }

    public void setLeavesBehind(Integer leavesBehind) {
        this.leavesBehind = leavesBehind;
    }

    public LocalDate getIncarceratedDate() {
        return incarceratedDate;
    }

    public void setIncarceratedDate(LocalDate incarceratedDate) {
        this.incarceratedDate = incarceratedDate;
    }

}
