package com.isa.pharmacies_system.DTO;

import com.isa.pharmacies_system.domain.rating.RatingScale;
import com.isa.pharmacies_system.domain.user.Dermatologist;

public class RatingStaffDTO extends RatingDTO{

    private Long staffId;
    private String staffFirstName;
    private String staffLastName;
    private String staffEmail;

    public RatingStaffDTO() {
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public String getStaffFirstName() {
        return staffFirstName;
    }

    public void setStaffFirstName(String staffFirstName) {
        this.staffFirstName = staffFirstName;
    }

    public String getStaffLastName() {
        return staffLastName;
    }

    public void setStaffLastName(String staffLastName) {
        this.staffLastName = staffLastName;
    }

    public String getStaffEmail() {
        return staffEmail;
    }

    public void setStaffEmail(String staffEmail) {
        this.staffEmail = staffEmail;
    }
}
