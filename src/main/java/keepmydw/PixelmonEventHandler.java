package keepmydw;

import java.util.ArrayList;
import java.util.List;

import com.pixelmonmod.pixelmon.api.events.EvolveEvent;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.pokemon.ability.Ability;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class PixelmonEventHandler {
	
	private List<ServerPlayerEntity> dwOwners = new ArrayList<>();
	
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void onEvolve(final EvolveEvent.Pre event) {
		
		Pokemon pokemon = event.getPokemon();
			
		if(pokemon == null || pokemon.getOwnerPlayer() ==null) {
			return;
		}
		
		ServerPlayerEntity player = pokemon.getOwnerPlayer();
		if(dwOwners.contains(player)){
			dwOwners.remove(player);
		}
		if(pokemon.hasHiddenAbility()) {
			dwOwners.add(player);
		}
	}
	
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void onEvolve(final EvolveEvent.Post event) {
		Pokemon pokemon = event.getPokemon();
		if(pokemon == null || pokemon.getOwnerPlayer() ==null) {
			return;
		}
		ServerPlayerEntity player = pokemon.getOwnerPlayer();
		if(dwOwners.contains(player)){
			try {
				Ability[] abilities = pokemon.getSpecies().getForm(pokemon.getForm().getName()).getAbilities().getHiddenAbilities();
				if(abilities == null || abilities.length <= 0) {
					return;
				}
				pokemon.setAbility(abilities[0]);
			} catch(Exception e) {
				KeepMyDW.LOGGER.error("Error trying to retrieve pokemon hidden ability");
				e.printStackTrace();
			}
			dwOwners.remove(player);
		}
	}
}