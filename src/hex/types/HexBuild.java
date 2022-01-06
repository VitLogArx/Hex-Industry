package hex.types;

import arc.func.*;
import arc.graphics.*;
import mindustry.gen.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.entities.units.*;

import static mindustry.Vars.*;

public class HexBuild {

	static {
		UnitTypes.poly.health = 1000000000;
		UnitTypes.poly.defaultController = BuilderAI::new;
	}

	public HexBuild next;
	public Schem scheme;
	public Effect boom;

	public Cons<Production> cons;

	public void build(Hex hex) {
		if (!hex.isEmpty()) {
			explode(hex);
			recons(hex);
		} // cleanup old build

		Unit poly = UnitTypes.poly.spawn(hex.owner.player.team(), hex.pos());
		scheme.each(st -> poly.addBuild(new BuildPlan(st.x + hex.x, st.y + hex.y, st.rotation, st.block, st.config)));

		cons.get(hex.owner.production);
		hex.clearButtons();

		if (next != null) hex.buttons.add(new Button((h, x) -> {
			x.build(next);
			x.open();
		}, hex, hex.cx, hex.cy));
	}

	public void explode(Hex hex) {
		float x = hex.cx * tilesize;
		float y = hex.cy * tilesize;

		Call.effect(boom, x, y, 0, Color.white);
		Call.soundAt(Sounds.explosionbig, x, y, 1, 1);

		Damage.damage(x, y, 13 * 8, 1000000);
	}

	public void recons(Hex hex) {
		hex.owner.production.reverse();
		hex.build.cons.get(hex.owner.production);
		hex.owner.production.reverse();
	}
}
