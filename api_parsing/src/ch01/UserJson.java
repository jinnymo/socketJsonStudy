package ch01;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserJson {
	private int id;
	private String username;
	private String email;
	
	private Address address;
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public class Address {
		private String street;
		private String suite;
		private String city;
		private String zipcode;
		
		public class geo{
			private String lat;
			private String lng;
		}

	}
	private String phone;
	private String website;
	public Company company;
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	
	public class Company{
		private String name;
		private String catchPhrase;
		private String bs;
	}

}
