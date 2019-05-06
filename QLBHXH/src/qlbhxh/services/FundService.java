package qlbhxh.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import qlbhxh.dao.FundDAO;
import qlbhxh.model.Fund;

/**
 * This class provide services for fund configs operators
 */
@Service
public class FundService {
	@Autowired
	private FundDAO fundDAO;
	
	Fund fund;
	
	/**
	 * This class gets all fund configs
	 * @return
	 */
	public Fund getFundConfig()
	{
		fund = fundDAO.getFundConfig();
		return fund;
	}
	
	/**
	 * This class updates all fund configs
	 * @param newFund - new fund configs
	 * @return true if success, false otherwise
	 */
	@Transactional(rollbackFor = Exception.class)
	public boolean updateFundConfig(Fund newFund)
	{
		if(fund.equals(newFund))
			return false;
		boolean result = fundDAO.updateFundConfig(newFund);
		if(result == true)
			fund = newFund;
		return result;
	}
}
