package com.example.customtime;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

public class TimeCommands {
    @SubscribeEvent

    public static void onRegister(RegisterCommandsEvent e) {
        CommandDispatcher<CommandSourceStack> d = e.getDispatcher();
        d.register(Commands.literal("customtime")
                .requires(src -> src.hasPermission(2))
                .then(Commands.literal("get").executes(ctx -> {
                    ctx.getSource().sendSuccess(() -> Component.literal(String.format(
                            "Day: %.3f, Night: %.3f, OverworldOnly: %s, FreezeGamerule: %s",
                            ModTimeConfig.dayScale(), ModTimeConfig.nightScale(),
                            ModTimeConfig.overworldOnly(), ModTimeConfig.freezeGamerule()
                    )), false);
                    return 1;
                }))
                .then(Commands.literal("set")
                        .then(Commands.literal("day")
                                .then(Commands.argument("value", DoubleArgumentType.doubleArg(0.0, 1000.0))
                                        .executes(ctx -> { double v = DoubleArgumentType.getDouble(ctx, "value"); ModTimeConfig.DAY_SCALE.set(v);
                                            ctx.getSource().sendSuccess(() -> Component.literal("Set day_scale=" + v), true); return 1; })))
                        .then(Commands.literal("night")
                                .then(Commands.argument("value", DoubleArgumentType.doubleArg(0.0, 1000.0))
                                        .executes(ctx -> { double v = DoubleArgumentType.getDouble(ctx, "value"); ModTimeConfig.NIGHT_SCALE.set(v);
                                            ctx.getSource().sendSuccess(() -> Component.literal("Set night_scale=" + v), true); return 1; })))
                        .then(Commands.literal("overworld_only")
                                .then(Commands.literal("true").executes(ctx -> { ModTimeConfig.OVERWORLD_ONLY.set(true);
                                    ctx.getSource().sendSuccess(() -> Component.literal("overworld_only=true"), true); return 1; }))
                                .then(Commands.literal("false").executes(ctx -> { ModTimeConfig.OVERWORLD_ONLY.set(false);
                                    ctx.getSource().sendSuccess(() -> Component.literal("overworld_only=false"), true); return 1; })))
                        .then(Commands.literal("freeze_gamerule")
                                .then(Commands.literal("true").executes(ctx -> { ModTimeConfig.FORCE_FREEZE_GAMERULE.set(true);
                                    ctx.getSource().sendSuccess(() -> Component.literal("force_freeze_gamerule=true"), true); return 1; }))
                                .then(Commands.literal("false").executes(ctx -> { ModTimeConfig.FORCE_FREEZE_GAMERULE.set(false);
                                    ctx.getSource().sendSuccess(() -> Component.literal("force_freeze_gamerule=false"), true); return 1; }))))
                .then(Commands.literal("save").executes(ctx -> {
                    ctx.getSource().sendSuccess(() -> Component.literal(
                            "Config values updated. They will be written to custom_time_settings.toml on shutdown."), true);
                    return 1;
                }))
        );
    }
}