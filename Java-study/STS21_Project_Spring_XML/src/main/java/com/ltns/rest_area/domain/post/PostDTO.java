package com.ltns.rest_area.domain.post;

import java.sql.Timestamp;

import com.ltns.rest_area.domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostDTO implements DTO {
	private int post_uid;
	private int mm_uid;	//작성자 - 회원 uid(외래키)
	private String post_title;
	private String post_contents;
	private Timestamp post_regdate;	
}
