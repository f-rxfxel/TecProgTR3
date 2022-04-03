package trabalho.periodo3;

public class Players {
	
	private String name;
	private String site;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}

	@Override
	public String toString() {
		return "\"" + name + "\" | site: " + site;
	}
	
}
