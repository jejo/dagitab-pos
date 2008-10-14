package bus;

public enum ComplianceMode	{ 
	
		DAILY("S","DAILY"),HOURLY("H","HOURLY"),DISCOUNT("D","DISCOUNT");
		
		ComplianceMode(String complianceSuffix, String name) {
			this.complianceSuffix = complianceSuffix;
			this.name = name;
			
		}
	 
		private String complianceSuffix;
		private String name;
		
		public String getComplianceSuffix() {
			return complianceSuffix;
		}

		public String getName() {
			return name;
		}
		
		
	 	

}

