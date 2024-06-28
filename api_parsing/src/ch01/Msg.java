package ch01;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Msg {
	private int clearVal;
	private int sn;
	private String districName;
	private String dataDate;
	private int issueVal;
	private String issueTime;
	private String clearDate;
	private String issueDate;
	private String moveName;
	private String clearTime;
	private String issueGbn;
	private String itemCode;

}
