package com.isa.pharmacies_system.service.iService;

import com.isa.pharmacies_system.domain.pharmacy.SystemLoyalty;

public interface ISystemLoyaltyService {
	SystemLoyalty save(SystemLoyalty systemLoyalty);
	SystemLoyalty get();
}
