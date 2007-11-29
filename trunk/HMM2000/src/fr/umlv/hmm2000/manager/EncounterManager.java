package fr.umlv.hmm2000.manager;

public class EncounterManager {

	
	public void perform(EncounterEvent event){
		System.out.println(event.getSender().getSprite());
		System.out.println(event.getRecipient().getSprite());
		System.out.println(event.getLocation());
	}
	
	
}
