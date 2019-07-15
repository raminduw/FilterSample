
package com.ramindu.weeraman.filter.sample.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserItem {

    @SerializedName("age")
    @Expose
    private Integer age;
    @SerializedName("city")
    @Expose
    private City city;
    @SerializedName("compatibility_score")
    @Expose
    private Double compatibilityScore;
    @SerializedName("contacts_exchanged")
    @Expose
    private Integer contactsExchanged;
    @SerializedName("display_name")
    @Expose
    private String displayName;
    @SerializedName("favourite")
    @Expose
    private Boolean favourite;
    @SerializedName("height_in_cm")
    @Expose
    private Integer heightInCm;
    @SerializedName("job_title")
    @Expose
    private String jobTitle;
    @SerializedName("main_photo")
    @Expose
    private String mainPhoto;
    @SerializedName("religion")
    @Expose
    private String religion;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Double getCompatibilityScore() {
        return compatibilityScore;
    }

    public void setCompatibilityScore(Double compatibilityScore) {
        this.compatibilityScore = compatibilityScore;
    }

    public Integer getContactsExchanged() {
        return contactsExchanged;
    }

    public void setContactsExchanged(Integer contactsExchanged) {
        this.contactsExchanged = contactsExchanged;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Boolean getFavourite() {
        return favourite;
    }

    public void setFavourite(Boolean favourite) {
        this.favourite = favourite;
    }

    public Integer getHeightInCm() {
        return heightInCm;
    }

    public void setHeightInCm(Integer heightInCm) {
        this.heightInCm = heightInCm;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getMainPhoto() {
        return mainPhoto;
    }

    public void setMainPhoto(String mainPhoto) {
        this.mainPhoto = mainPhoto;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

}
