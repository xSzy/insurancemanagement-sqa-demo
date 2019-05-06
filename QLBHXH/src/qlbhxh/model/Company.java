/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlbhxh.model;

/**
 *
 * @author xSzy
 */
public class Company
{
    private int ID;
    private String companyName;
    private String companyInsuranceID;

    public int getID()
    {
        return ID;
    }

    public void setID(int ID)
    {
        this.ID = ID;
    }

    public String getCompanyName()
    {
        return companyName;
    }

    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }

    public String getCompanyInsuranceID()
    {
        return companyInsuranceID;
    }

    public void setCompanyInsuranceID(String companyInsuranceID)
    {
        this.companyInsuranceID = companyInsuranceID;
    }
    
}
