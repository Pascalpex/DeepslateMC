package de.pascalpex.deepslatemc.files;

import java.util.List;

enum MessagesEntry {
    PREFIX("prefix", "&0[&8DeepslateMC&0]"),
    WRONG_SYNTAX("wrongSyntax", "&cWrong usage, use: &b%usage%"),
    CONFIG_RELOADED("configReloaded", "&aConfig got reloaded successfully"),
    ONLY_FOR_PLAYERS("onlyForPlayers", "&cThis command can only be used by players"),
    DISCORD_MESSAGE("discordMessage", "&6The link to our Discord server: &b%link%"),
    NO_PERMISSIONS("noPermissions", "&cYou have no permission to do this"),
    CLEARED_CHAT("clearedChat", "&6The chat got cleared by &b%clearer%"),
    BUILDWORLD_SET("buildworldSet", "&aThe buildworld got set successfully"),
    BUILDWORLD_WELCOME("buildworldWelcome", "&aWelcome to the buildworld"),
    BUILDWORLD_NOT_SET("buildworldNotSet", "&cThe buildworld is not set"),
    MAINTENANCE_KICK("maintenanceKick", "&cThe server is currently in maintenance mode"),
    MAINTENANCE_ON("maintenanceOn", "&aYou turned on the maintenance mode"),
    MAINTENANCE_OFF("maintenanceOff", "&aYou turned off the maintenance mode"),
    HELP_MESSAGE("helpMessage", List.of("&6This is a custom help message.", "&6Configure it in the &bmessages.yml &6file inside the deepslate folder.")),
    SPAWN_SET("spawnSet", "&aThe spawn got set successfully"),
    SPAWN_NOT_SET("spawnNotSet", "&cThe spawn is not set"),
    SPAWN_TELEPORTED("spawnTeleport", "&aYou got teleported to the spawn");

    final String key;
    final Object defaultValue;

    MessagesEntry(String key, Object defaultValue) {
        this.key = key;
        this.defaultValue = defaultValue;
    }
}
