package com.ltns.rest_area.domain.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class AjaxList extends com.ltns.rest_area.domain.AjaxList {

	@JsonProperty("data")
	List listAll;	//dto 데이터

}
