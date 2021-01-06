package ntro.messages;

public abstract class RecepteurMessage<MR extends MessageRecu> {

	public abstract void recevoirMessage(MR messageRecu);

}
