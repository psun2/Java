package an.adminSettings;


public class AdminSettingsControl {
	
	AdminSettingsView settingsview;
	
  public AdminSettingsControl() {
      
	  settingsview = new AdminSettingsView(this);
	  settingsview.main();
  	}	
	 
}
