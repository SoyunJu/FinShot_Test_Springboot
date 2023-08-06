package erp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import erp.dto.ErpDto;
import erp.mapper.ErpMapper;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ErpServiceImpl implements ErpService {

	@Autowired
	ErpMapper erpMapper;

	@Override
	public int insertInfo(ErpDto erpDto) {
		return erpMapper.insertInfo(erpDto);
	}

	@Override
	public List<ErpDto> erpList() {
		List<ErpDto> erpDtoList = erpMapper.erpList();
		return erpDtoList;
	}

	@Override
	public int delInfo(int emNum) {
		return erpMapper.delInfo(emNum);
	}

	@Override
	public int updateInfo(ErpDto erpDto) {
		return erpMapper.updateInfo(erpDto);
	}

	@Override
	public List<ErpDto> getPerson(int emNum) {
		return erpMapper.getPerson(emNum);
	}

	@Override
	public int checkDupl(int emNum) {
		return erpMapper.checkDupl(emNum);
	}

	@Override
	public List<String[]> listString() {
		List<ErpDto> list = erpMapper.erpList();
		List<String[]> listStrings = new ArrayList<>();
		listStrings.add(new String[] { "직원번호", "직급", "이름", "전화번호", "이메일" });
		for (ErpDto erp : list) {
			String[] rowData = new String[5];
			rowData[0] = String.valueOf(erp.getEmNum());
			rowData[1] = erp.getEmRank();
			rowData[2] = erp.getName();
			rowData[3] = erp.getPhoneNum();
			rowData[4] = erp.getEmail();
			listStrings.add(rowData);
		}
		return listStrings;
	}

}
