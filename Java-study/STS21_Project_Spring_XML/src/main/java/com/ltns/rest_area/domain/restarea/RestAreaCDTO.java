package com.ltns.rest_area.domain.restarea;

import com.ltns.rest_area.domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RestAreaCDTO implements DTO {
	private String routeName;
	private String destination;
	
	private String orderBy;		//경로순 'BY_PATH', 좋아요순 'BY_LIKE'
								//GS용	'BY_DIESEL' 'BY_GASOLINE' 'BY_LPG'
								//FM용	'BY_PRICE'
	private int from;
	private int numOfRows;
	
	
}
