package hex.types;

import hex.*;
import mindustry.gen.*;
import mindustry.game.*;
import mindustry.world.*;
import mindustry.content.*;

import static mindustry.Vars.*;

public class Human {

	protected static int id;

	public Player player;
	public Hex citadel;
	public Fraction fraction;

	public Human(Player ppl, Fraction abilities) {
		player = ppl;
		fraction = abilities;
	}

	public void init(Hex hex) {
		this.citadel = hex;
		player.team(Team.baseTeams[id++]);

		// TODO: move to hex.build
		world.tile(hex.cx, hex.cy).setNet(Blocks.coreNucleus, player.team(), 0);
		Schems.citadel.tiles.each(st -> {
			Tile tile = world.tile(st.x + hex.x + 2, st.y + hex.y + 3);
			tile.setNet(st.block, player.team(), 0);
			if (st.config != null) tile.build.configureAny(st.config);
		});
	}
}
