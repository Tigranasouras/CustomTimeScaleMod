package com.example.customtime;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;

@Mod(CustomTimeMod.MODID) //makes NeoForge load the mod class
public class CustomTimeMod {
    public static final String MODID = "customtime";

    //MDK injects IEventBus and ModContainer here
    public CustomTimeMod(IEventBus modEventBus, ModContainer modContainer){

        //Registers config at config/customtime-server.toml (filename is auto-derived from mod id)
        modContainer.registerConfig(ModConfig.Type.SERVER, ModTimeConfig.SPEC, "custom_time_settings.toml");


        //Register command handler for the RegisterCommandsEvent /customtime ...
        NeoForge.EVENT_BUS.register(TimeCommands.class);
    }

}
