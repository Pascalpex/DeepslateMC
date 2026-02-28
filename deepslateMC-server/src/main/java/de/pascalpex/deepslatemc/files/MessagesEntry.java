package de.pascalpex.deepslatemc.files;

import java.util.List;

public enum MessagesEntry {
    PREFIX("prefix", "<color:#222222>[<gradient:white:dark_gray>DeepslateMC<color:#222222>]"),
    WRONG_SYNTAX("wrongSyntax", "<red>Wrong usage, use: <aqua>%usage%"),
    CONFIG_RELOADED("configReloaded", "<green>Config got reloaded successfully"),
    ONLY_FOR_PLAYERS("onlyForPlayers", "<red>This command can only be used by players"),
    DISCORD_MESSAGE("discordMessage", "<gold>The link to our Discord server: <aqua>%link%"),
    CLEARED_CHAT("clearedChat", "<gold>The chat got cleared by <aqua>%clearer%"),
    BUILDWORLD_SET("buildworldSet", "<green>The buildworld got set successfully"),
    BUILDWORLD_WELCOME("buildworldWelcome", "<green>Welcome to the buildworld"),
    BUILDWORLD_NOT_SET("buildworldNotSet", "<red>The buildworld is not set"),
    MAINTENANCE_KICK("maintenanceKick", "<red>The server is currently in maintenance mode"),
    MAINTENANCE_ON("maintenanceOn", "<green>You turned on the maintenance mode"),
    MAINTENANCE_OFF("maintenanceOff", "<green>You turned off the maintenance mode"),
    HELP_MESSAGE("helpMessage", List.of("<gold>This is a custom help message.", "<gold>Configure it in the <aqua>messages.yml <gold>file inside the deepslate folder.")),
    SPAWN_SET("spawnSet", "<green>The spawn got set successfully"),
    SPAWN_NOT_SET("spawnNotSet", "<red>The spawn is not set"),
    SPAWN_TELEPORTED("spawnTeleport", "<green>You got teleported to the spawn"),
    NO_ITEM("noItem", "<red>No valid item was found"),
    ITEM_UNBREAKABLE("itemUnbreakable", "<green>The item is now unbreakable"),;

    final String key;
    final Object defaultValue;

    MessagesEntry(String key, Object defaultValue) {
        this.key = key;
        this.defaultValue = defaultValue;
    }
}
