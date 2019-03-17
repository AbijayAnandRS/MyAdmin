package com.example.myadmin.data;


import lombok.Data;

@Data
public class ClientData {
    
    public final String companyName;
    public final String companyEmail;
    public String password;
    public String companyWebsite;
    public String country;
    public String companyContactId;
    public String companyDetails;
    public String companyAddress;
    public String companyPhone;
    public String companyLogoUrl;
}
