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
public class Fund
{
    private double requiredODTS;
    private double companyODTS;
    private double willingODTS;
    
    private double requiredTNLD;
    private double companyTNLD;
    private double willingTNLD;
    
    private double requiredHTTT;
    private double companyHTTT;
    private double willingHTTT;
	public double getRequiredODTS() {
		return requiredODTS;
	}
	public void setRequiredODTS(double requiredODTS) {
		this.requiredODTS = requiredODTS;
	}
	public double getCompanyODTS() {
		return companyODTS;
	}
	public void setCompanyODTS(double companyODTS) {
		this.companyODTS = companyODTS;
	}
	public double getWillingODTS() {
		return willingODTS;
	}
	public void setWillingODTS(double willingODTS) {
		this.willingODTS = willingODTS;
	}
	public double getRequiredTNLD() {
		return requiredTNLD;
	}
	public void setRequiredTNLD(double requiredTNLD) {
		this.requiredTNLD = requiredTNLD;
	}
	public double getCompanyTNLD() {
		return companyTNLD;
	}
	public void setCompanyTNLD(double companyTNLD) {
		this.companyTNLD = companyTNLD;
	}
	public double getWillingTNLD() {
		return willingTNLD;
	}
	public void setWillingTNLD(double willingTNLD) {
		this.willingTNLD = willingTNLD;
	}
	public double getRequiredHTTT() {
		return requiredHTTT;
	}
	public void setRequiredHTTT(double requiredHTTT) {
		this.requiredHTTT = requiredHTTT;
	}
	public double getCompanyHTTT() {
		return companyHTTT;
	}
	public void setCompanyHTTT(double companyHTTT) {
		this.companyHTTT = companyHTTT;
	}
	public double getWillingHTTT() {
		return willingHTTT;
	}
	public void setWillingHTTT(double willingHTTT) {
		this.willingHTTT = willingHTTT;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(companyHTTT);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(companyODTS);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(companyTNLD);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(requiredHTTT);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(requiredODTS);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(requiredTNLD);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(willingHTTT);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(willingODTS);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(willingTNLD);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fund other = (Fund) obj;
		if (Double.doubleToLongBits(companyHTTT) != Double.doubleToLongBits(other.companyHTTT))
			return false;
		if (Double.doubleToLongBits(companyODTS) != Double.doubleToLongBits(other.companyODTS))
			return false;
		if (Double.doubleToLongBits(companyTNLD) != Double.doubleToLongBits(other.companyTNLD))
			return false;
		if (Double.doubleToLongBits(requiredHTTT) != Double.doubleToLongBits(other.requiredHTTT))
			return false;
		if (Double.doubleToLongBits(requiredODTS) != Double.doubleToLongBits(other.requiredODTS))
			return false;
		if (Double.doubleToLongBits(requiredTNLD) != Double.doubleToLongBits(other.requiredTNLD))
			return false;
		if (Double.doubleToLongBits(willingHTTT) != Double.doubleToLongBits(other.willingHTTT))
			return false;
		if (Double.doubleToLongBits(willingODTS) != Double.doubleToLongBits(other.willingODTS))
			return false;
		if (Double.doubleToLongBits(willingTNLD) != Double.doubleToLongBits(other.willingTNLD))
			return false;
		return true;
	}
    
    
}
