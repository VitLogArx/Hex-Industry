package hex.content;

import hex.types.HexBuild;
import hex.types.Production;
import mindustry.content.Fx;

public class HexBuilds {

    public static HexBuild citadel, miner, thory, oil, cryo, compressor;

    public static void load() {
        citadel = new HexBuild() {{
            name = "Citadel Mk1";
            scheme = HexSchematics.citadelMk1;
            boom = Fx.impactReactorExplosion;

            prod = new Production() {{
                human = 20;
            }};
            cons = new Production();

            next = new HexBuild() {{
                name = "Citadel Mk2";
                scheme = HexSchematics.citadelMk3;
                boom = Fx.impactReactorExplosion;

                prod = new Production() {{
                    human = 20;
                }};
                cons = new Production() {{
                    plastanium = 20;
                }};
            }};
        }};

        miner = new HexBuild() {{
            name = "Miner Mk1";
            scheme = HexSchematics.minerMk1;
            boom = Fx.reactorExplosion;

            prod = new Production() {{
                titanium = 1;
            }};
            cons = new Production() {{
                human = 1;
            }};

            next = new HexBuild() {{
                name = "Miner Mk2";
                scheme = HexSchematics.minerMk2;
                boom = Fx.reactorExplosion;

                prod = new Production() {{
                    titanium = 2;
                }};
                cons = new Production() {{
                    plastanium = 20;
                    human = 2;
                }};

                next = new HexBuild() {{
                    name = "Miner Mk3";
                    scheme = HexSchematics.minerMk3;
                    boom = Fx.reactorExplosion;

                    prod = new Production() {{
                        titanium = 3;
                    }};
                    cons = new Production() {{
                        spore = 20;
                        human = 3;
                    }};
                }};
            }};
        }};

        thory = new HexBuild() {{
            name = "Miner Mk1";
            scheme = HexSchematics.thoryMk1;
            boom = Fx.reactorExplosion;

            prod = new Production() {{
                thorium = 1;
            }};
            cons = new Production() {{
                titanium = 20;
                human = 1;
            }};

            next = new HexBuild() {{
                name = "Miner Mk2";
                scheme = HexSchematics.thoryMk2;
                boom = Fx.reactorExplosion;

                prod = new Production() {{
                    thorium = 2;
                }};
                cons = new Production() {{
                    plastanium = 20;
                    human = 2;
                }};
            }};
        }};

        oil = new HexBuild() {{
            name = "Oil Pump";
            scheme = HexSchematics.oil;
            boom = Fx.impactcloud;

            prod = new Production() {{
                oil = 1;
            }};
            cons = new Production() {{
                thorium = 20;
                human = 1;
            }};
        }};

        cryo = new HexBuild() {{
            name = "Cryo Pump";
            scheme = HexSchematics.cryo;
            boom = Fx.impactcloud;

            prod = new Production() {{
                cryo = 1;
            }};
            cons = new Production() {{
                thorium = 20;
                human = 1;
            }};
        }};

        compressor = new HexBuild() {{
            name = "Compressor";
            scheme = HexSchematics.compressor;
            boom = Fx.impactcloud;

            prod = new Production() {{
                plastanium = 1;
            }};
            cons = new Production() {{
                titanium = 20;
                oil = 1;
                human = 1;
            }};
        }};
    }
}
