package com.example.customtime;

import net.neoforged.neoforge.common.ModConfigSpec;
public class ModTimeConfig {
    public static final ModConfigSpec SPEC;
    public static final ModConfigSpec.DoubleValue DAY_SCALE;
    public static final ModConfigSpec.DoubleValue NIGHT_SCALE;
    public static final ModConfigSpec.BooleanValue OVERWORLD_ONLY;
    public static final ModConfigSpec.BooleanValue FORCE_FREEZE_GAMERULE;

    static{
        ModConfigSpec.Builder b = new ModConfigSpec.Builder();
        b.push("timescale");
        DAY_SCALE = b.defineInRange("day_scale", 1.0D, 0.0D, 1000.0D); //the default Time Scale on a new world
        NIGHT_SCALE = b.defineInRange("night_scale", 1.0D, 0.0D, 1000.0D);
        OVERWORLD_ONLY = b.define("overworld_only", true);
        FORCE_FREEZE_GAMERULE = b.define("force_freeze_gamerule", true);
        b.pop();
        SPEC = b.build();
    }

    public static double dayScale() {return DAY_SCALE.get(); }
    public static double nightScale(){return NIGHT_SCALE.get(); }
    public static boolean overworldOnly() {return OVERWORLD_ONLY.get(); }
    public static boolean freezeGamerule() {return FORCE_FREEZE_GAMERULE.get(); }


}
