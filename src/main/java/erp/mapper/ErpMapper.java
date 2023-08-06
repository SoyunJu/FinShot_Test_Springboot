package erp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import erp.dto.ErpDto;

@Mapper
public interface ErpMapper {

	int insertInfo(ErpDto erpDto);

	List<ErpDto> erpList();

	int delInfo(int emNum);

	int updateInfo(ErpDto erpDto);

	List<ErpDto> getPerson(int emNum);

	int checkDupl(int emNum);

}
