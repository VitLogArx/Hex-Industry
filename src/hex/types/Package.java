package hex.types;

import arc.func.Cons;
import arc.func.Func;
import arc.util.Time;

import static hex.components.Bundle.get;
import static hex.components.Bundle.format;

public class Package {

    public static final float delay = 2f * Time.toMinutes;

    public String name;
    public int cost;

    public Cons<Human> cont;
    public Func<Human, String> desc;
    public Func<Human, Boolean> cons = human -> human.production.crawler(human, cost);
    public Func<Human, Boolean> pred = human -> true;

    public void send(Human human) {
        if (!cons.get(human)) return;

        human.player.sendMessage(format("shop.pack.in", human.locale, get(name + ".name", human.locale)));
        Time.run(delay, () -> got(human));
    }

    public void got(Human human) {
        human.player.sendMessage(format("shop.pack.got", human.locale, get(name + ".name", human.locale)));
        cont.get(human);
    }
}