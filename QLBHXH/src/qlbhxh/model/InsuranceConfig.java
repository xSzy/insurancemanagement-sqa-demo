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
public class InsuranceConfig
{
    private int ID;
    private float interestRate;
    private int minimumIncome;
    private int maximumIncome;

    public int getID()
    {
        return ID;
    }

    public void setID(int ID)
    {
        this.ID = ID;
    }

    public float getInterestRate()
    {
        return interestRate;
    }

    public void setInterestRate(float interestRate)
    {
        this.interestRate = interestRate;
    }

    public int getMinimumIncome()
    {
        return minimumIncome;
    }

    public void setMinimumIncome(int minimumIncome)
    {
        this.minimumIncome = minimumIncome;
    }

    public int getMaximumIncome()
    {
        return maximumIncome;
    }

    public void setMaximumIncome(int maximumIncome)
    {
        this.maximumIncome = maximumIncome;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(interestRate);
		result = prime * result + maximumIncome;
		result = prime * result + minimumIncome;
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
		InsuranceConfig other = (InsuranceConfig) obj;
		if (Float.floatToIntBits(interestRate) != Float.floatToIntBits(other.interestRate))
			return false;
		if (maximumIncome != other.maximumIncome)
			return false;
		if (minimumIncome != other.minimumIncome)
			return false;
		return true;
	}

    
}
