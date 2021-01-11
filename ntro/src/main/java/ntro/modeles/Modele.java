package ntro.modeles;

public abstract class Modele<MLS extends ModeleLectureSeule> implements ModeleLectureSeule {
	
	public abstract String getId();
	public abstract void apresCreation();
	public abstract void apresChargementJson();
	
}