package bus;

public enum ComplianceMode	{ 
		DAILY("S"),HOURLY("H"),DISCOUNT("D");
		
		ComplianceMode(String complianceSuffix) {
			this.complianceSuffix = complianceSuffix;
		}
	 
		private String complianceSuffix;
		
		public String getComplianceSuffix() {
			return complianceSuffix;
		}
	 	

}

