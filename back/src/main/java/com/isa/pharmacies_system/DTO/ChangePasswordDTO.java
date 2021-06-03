package com.isa.pharmacies_system.DTO;

public class ChangePasswordDTO {

    private Long id;
    private String newPassword;
    private String confirmationOfNewPassword;

    public ChangePasswordDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmationOfNewPassword() {
        return confirmationOfNewPassword;
    }

    public void setConfirmationOfNewPassword(String confirmationOfNewPassword) {
        this.confirmationOfNewPassword = confirmationOfNewPassword;
    }
}
