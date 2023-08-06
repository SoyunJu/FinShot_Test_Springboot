package erp.dto;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ErpDto {
	
	private int infoIdx;
	
	@NotBlank
	private int emNum;
	
	@NotBlank
	private String emRank;
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String phoneNum;
	
	@NotBlank
	private String email;
	
		public void setPhoneNum(String phoneNum) {
			if (!isValidPhoneNum(phoneNum)) {
				throw new IllegalArgumentException("연락처 형식이 맞지 않습니다.");
			}
			this.phoneNum = phoneNum;
		}

		public void setEmail(String email) {
			if (!isValidEmail(email)) {
				throw new IllegalArgumentException("이메일 형식이 맞지 않습니다.");
			}
			this.email = email;
		}

		private boolean isValidPhoneNum(String phoneNum) {
			Pattern pattern = Pattern.compile("^\\d{3}-\\d{4}-\\d{4}$");
			Matcher matcher = pattern.matcher(phoneNum);
			return matcher.matches();
		}

		private boolean isValidEmail(String email) {
			Pattern pattern = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
			Matcher matcher = pattern.matcher(email);
			return matcher.matches();
		}

}
