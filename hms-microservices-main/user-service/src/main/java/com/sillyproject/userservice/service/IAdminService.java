package com.sillyproject.userservice.service;

import com.sillyproject.userservice.dto.AdminDto;
import com.sillyproject.userservice.payload.ApiResponse;

public interface IAdminService {	
	AdminDto viewAdmin();
	ApiResponse updateAdmin(AdminDto adminDto);
}
