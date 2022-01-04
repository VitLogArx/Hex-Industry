package hex.types;

import hex.*;
import hex.content.*;
import arc.*;
import arc.struct.*;
import mindustry.gen.*;
import mindustry.game.*;
import mindustry.game.EventType.*;
import mindustry.content.*;

import static mindustry.Vars.*;

public class Human {

	protected static int _id;

	static ObjectMap<Player, Unit> units = new ObjectMap<>();
	static {
		Events.on(UnitChangeEvent.class, event -> {
			if (!Main.initialized) return;

			Unit unit = units.get(event.player);
			if (event.unit != unit)
				event.player.unit(unit);
		});
	}

	public Player player;
	public Hex citadel;
	public Fraction fraction;
	public Production production;

	public Human(Player ppl, Fraction abilities) {
		player = ppl;
		fraction = abilities;
	}

	public void init(Hex hex) {
		// TODO: Team.all
		player.team(Team.baseTeams[_id++]);

		// spawns fraction's unit
		player.unit(fraction.spawn(player.team(), hex.pos()));
		
		// saves the player's unit
		units.put(player, player.unit());

		world.tile(hex.cx, hex.cy).setNet(Blocks.coreNucleus, player.team(), 0);
		hex.owner = this;
		hex.build(HexBuilds.citadel);

		citadel = hex;
		production = new Production(this);
	}

	public Hex location() {
		return Main.hexes.min(hex -> player.dst2(hex.pos()));
	}
}
