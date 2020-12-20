
package com.ltns.rest_area.domain.admin;



import com.ltns.rest_area.domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDTO implements DTO {
	
	private String subject;
	private String startDate; 
	private String endDate;
	private String memo;
	
	
	
	
	
	
	
	
	
	
	
}
