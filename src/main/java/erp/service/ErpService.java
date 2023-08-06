package erp.service;

import java.util.List;

import erp.dto.ErpDto;

public interface ErpService {

	int insertInfo(ErpDto erpDto);

	List<ErpDto> erpList();

	int delInfo(int emNum);

	int updateInfo(ErpDto erpDto);

	List<ErpDto> getPerson(int emNum);

	int checkDupl(int emNum);

	List<String[]> listString();
	
	

}
