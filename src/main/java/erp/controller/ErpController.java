package erp.controller;

import java.io.OutputStreamWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.opencsv.CSVWriter;

import erp.dto.ErpDto;
import erp.service.ErpService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class ErpController {

	@Autowired
	ErpService erpService;

	@PostMapping("/insertInfo")
	public ResponseEntity<String> insertInfo(@RequestBody ErpDto erpDto) throws Exception {

		int emNumExist = erpService.checkDupl(erpDto.getEmNum());

		if (emNumExist == 0) {

			int insertCount = erpService.insertInfo(erpDto);
			if (insertCount > 0) {
				return ResponseEntity.status(HttpStatus.CREATED).body("등록 성공");
			} else {
				return ResponseEntity.status(HttpStatus.OK).body("등록 실패");
			}
		} else {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("중복된 사번이 입니다.");
		}
	}

	@GetMapping("/")
	public ResponseEntity<List<ErpDto>> erpList() throws Exception {
		List<ErpDto> erpDto = erpService.erpList();
		return ResponseEntity.status(HttpStatus.OK).body(erpDto);
	}

	@DeleteMapping("/del/{emNum}")
	public ResponseEntity<String> delInfo(@PathVariable("emNum") int emNum) throws Exception {
		int delCount = erpService.delInfo(emNum);
		if (delCount > 0) {
			return ResponseEntity.status(HttpStatus.CREATED).body("삭제 성공");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("삭제 실패");
		}
	}

	@PutMapping("/update/{infoIdx}")
	public ResponseEntity<String> updateInfo(@PathVariable("infoIdx") int infoIdx, @RequestBody ErpDto erpDto)
			throws Exception {
		erpDto.setInfoIdx(infoIdx);
		int updateCount = erpService.updateInfo(erpDto);
		if (updateCount > 0) {
			return ResponseEntity.status(HttpStatus.CREATED).body("수정 성공");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("수정 실패");
		}
	}

	@GetMapping("/getPerson/{emNum}")
	public ResponseEntity<List<ErpDto>> getPerson(@PathVariable("emNum") int emNum) throws Exception {
		List<ErpDto> erpDto = erpService.getPerson(emNum);
		return ResponseEntity.status(HttpStatus.OK).body(erpDto);
	}

	@GetMapping("/csvDownload")
	public void csvDownload(HttpServletResponse response) throws Exception {
		response.setContentType("text/csv; charset=UTF-8");
		String fileName = URLEncoder.encode("직원리스트.csv", "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

		OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream(), StandardCharsets.UTF_8);
		writer.write("\uFEFF");
		CSVWriter csvWriter = new CSVWriter(writer);

		csvWriter.writeAll(erpService.listString());

		csvWriter.close();
		writer.close();
	}
}
