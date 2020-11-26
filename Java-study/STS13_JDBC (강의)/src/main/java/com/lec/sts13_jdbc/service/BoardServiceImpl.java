package com.lec.sts13_jdbc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lec.sts13_jdbc.domain.WriteDAO;
import com.lec.sts13_jdbc.domain.WriteDTO;

import lombok.Data;

@Service // 컨테이너의 빈으로 생성 됩니다.
@Data
public class BoardServiceImpl implements BoardService {

	@Autowired
	WriteDAO dao; // WriteDAOImpl 이 WriteDAO 의 구현체 이므로,
	// 여기와 서 꽃힙니다.

	public BoardServiceImpl() {
		System.out.println("BoardService() 생성");
	}

	@Override
	public List<WriteDTO> list() {
		return dao.select();
	}

	@Override
	public int write(WriteDTO dto) {
		return dao.insert(dto);
	}

	@Override
	public List<WriteDTO> viewByUid(long uid) {
		return dao.readByUid(uid);
	}

	@Override
	public List<WriteDTO> selectByUid(long uid) {
		return dao.selectByUid(uid);
	}

	@Override
	public int update(WriteDTO dto) {
		return dao.update(dto);
	}

	@Override
	public int deleteByUid(long uid) {
		return dao.deleteByUid(uid);
	}

}
