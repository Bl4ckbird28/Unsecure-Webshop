package de.fhws.biedermann.webshop.models;

public class UpdatePasswordPayload implements IModel {
    String oldPassword;
    String newPassword;

    public UpdatePasswordPayload() {}

    public UpdatePasswordPayload(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
