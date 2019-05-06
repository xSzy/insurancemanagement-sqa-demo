package qlbhxh.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import qlbhxh.dao.ConfigDAO;
import qlbhxh.model.InsuranceConfig;

/*
 * This class provide services for configuration operators
 */
@Service
public class ConfigService {
	@Autowired
	private ConfigDAO configDAO;
	
	InsuranceConfig config;
	
	/**
	 * This class get all configs from DAO
	 * @return all config
	 */
	public InsuranceConfig getConfig()
	{
		config = configDAO.getConfig();
		return config;
	}
	
	/**
	 * This class update new config
	 * @param newConfig - config to be applied
	 * @return true if success, false otherwise
	 */
	@Transactional(rollbackFor = Exception.class)
	public boolean updateConfig(InsuranceConfig newConfig)
	{
		if(config.equals(newConfig))
			return false;
		boolean result = configDAO.updateConfig(newConfig);
		if(result == true)
			config = newConfig;
		return result;
	}
}
