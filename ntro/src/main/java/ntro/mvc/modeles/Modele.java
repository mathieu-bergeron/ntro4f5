package ntro.mvc.modeles;

public abstract class Modele<MLS extends ModeleLectureSeule> implements ModeleLectureSeule {
	
	private String id;
	
	void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	public abstract void apresCreation();
	public abstract void apresChargementJson();
	
}