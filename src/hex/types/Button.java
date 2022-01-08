package hex.types;

import hex.content.*;
import arc.func.*;
import mindustry.world.*;

public class Button {

	public Cons2<Human, Hex> clicked;
	public Hex hex;

	private final int x;
	private final int y;

	public Button(Cons2<Human, Hex> clicked, Hex hex) {
		this(clicked, hex, hex.cx, hex.cy);
	}

	public Button(Cons2<Human, Hex> clicked, Hex hex, int x, int y) {
		this.clicked = clicked;
		this.hex = hex;

		this.x = x;
		this.y = y;

		Schems.button.floorNet(x, y);
		Buttons.register(this);
	}

	public void check(Tile tile, Human human) {
		if (bounds(tile.x, tile.y)) clicked.get(human, hex);
	}

	public boolean bounds(int ix, int iy) {
		return ix >= x - 1 && ix <= x + 1 && iy >= y - 1 && iy <= y + 1;
	}
}
