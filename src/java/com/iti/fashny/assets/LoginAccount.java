/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.assets;

import com.iti.fashny.entities.Client;
import com.iti.fashny.entities.Company;
import com.iti.fashny.entities.Partener;
import com.iti.fashny.entities.Tag;
import com.iti.fashny.interfaces.UserAccount;
import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Abdalla
 */
public class LoginAccount {

    private Role role;
    private String email;
    private BufferedImage personalImage;
    private List<Tag> intersets;
    private Date lastSeen;
    private UserAccount account;

    public LoginAccount(UserAccount userAccount) {
        this.account = userAccount;
    }

    public LoginAccount(Role role, String email, BufferedImage personalImage, List<Tag> intersets, Date lastSeen) {
        this.role = role;
        this.email = email;
        this.personalImage = personalImage;
        this.intersets = intersets;
        this.lastSeen = lastSeen;
    }

    private void spcifyRoleOfAccount(UserAccount account) {
        if (account instanceof Client) {
            role = Role.Client;
        } else if (account instanceof Company) {
            role = Role.Company;

        } else if (account instanceof Partener) {
            role = Role.Partner;
        }

    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BufferedImage getPersonalImage() {
        return personalImage;
    }

    public void setPersonalImage(BufferedImage personalImage) {
        this.personalImage = personalImage;
    }

    public List<Tag> getIntersets() {
        return intersets;
    }

    public void setIntersets(List<Tag> intersets) {
        this.intersets = intersets;
    }

    public Date getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(Date lastSeen) {
        this.lastSeen = lastSeen;
    }

}
