package hex;

import hex.types.*;
import arc.math.*;
import arc.math.geom.*;
import arc.struct.*;
import mindustry.gen.*;
import mindustry.game.*;
import mindustry.world.*;
import mindustry.content.*;

import static hex.Main.*;
import static mindustry.Vars.*;

public class Generator {

	protected static int last;

	public static void generate(int size) {
		MapSize m = MapSize.values()[size];
		world.loadGenerator(m.width, m.height, tiles -> tiles.each((x, y) -> tiles.set(x, y, new Tile(x, y, Blocks.air, Blocks.air, Blocks.darkMetal))));

		Point2 start = new Point2();
		Point2 point = new Point2();

		while (true) {
			hexes.add(new Hex(point.x, point.y));
			point.add(38, 0);

			if (Hex.bounds(point.x, point.y)) {
				start.add(19 * (start.x == 0 ? 1 : -1), 11);
				point.set(start);

				if (Hex.bounds(start.x, start.y)) break;
			}
		}
	}

	public static Hex citadel(Player player) {
		Hex hex = citadel();

		hex.env = Hex.HexEnv.citadel;
		hex.door = (byte) 0x00FFFFFF;
		hex.open();

		player.team(team());
		world.tile(hex.cx, hex.cy).setNet(Blocks.coreNucleus, player.team(), 0);

		return hex;
	}

	public static Hex citadel() {
		Seq<Hex> closed = hexes.copy().filter(Hex::isClosed);
		return closed.sort(h -> humans.sumf(p -> {
			float dst = h.point().dst(p.citadel.point());
			return dst > 100f ? -dst : Mathf.sqr(100f - dst);
		})).get(Mathf.random(humans.isEmpty() ? closed.size - 1 : closed.size / humans.size + 2));
	}

	public static Team team() {
		return Team.all[++last];
	}

	public enum MapSize {
		small(198, 201), medium(369, 366), big(540, 542);

		public final int width;
		public final int height;

		MapSize(int width, int height) {
			this.width = width;
			this.height = height;
		}
	}
}
