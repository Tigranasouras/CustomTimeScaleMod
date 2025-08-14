
Cloned a template repository to simplify the NeoForge Environment setup.
===================================================================================
Custom Time Scale is a lightweight NeoForge mod for Minecraft 1.21.x that gives you full control over the length of day and night — independently — without messing with game tick speed.

Unlike other time mods that slow the whole game, this one only changes the in-game clock, keeping crop growth, mob AI, and MineColonies colonists working normally.

Commands:

        /customtime get

        /customtime set day <scale> (1.0 = normal, 4.0 = 4× slower, 0.5 = 2× faster)

        /customtime set night <scale>

        /customtime overworld_only true|false

        /customtime freeze_gamerule true|false

         

        Notes:

            Per-world config lives at serverconfig/custom_time_settings.toml.

            Ship defaults with defaultconfigs/custom_time_settings.toml.

            If doDaylightCycle is true, vanilla time advance overrides scaling (toggle off or enable auto-freeze in config).
