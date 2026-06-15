package de.pascalpex.deepslatemc.files;

import net.kyori.adventure.bossbar.BossBar;

import java.util.List;

enum ConfigEntry {
    DISCORD_LINK("discordLink", "https://deepslatemc.de/dc/"),
    BUILDWORLD("buildworld", null),
    MAINTENANCE_MODE("maintenance", false),
    OP_COMMAND_ACTIVE("opCommandActive", true),
    SPAWN_ON_JOIN("spawnOnJoin", false),
    SPAWN_WORLD("spawn" + ".world", null),
    SPAWN_X("spawn" + ".X", null),
    SPAWN_Y("spawn" + ".Y", null),
    SPAWN_Z("spawn" + ".Z", null),
    SPAWN_PITCH("spawn" + ".pitch", null),
    SPAWN_YAW("spawn" + ".yaw", null),
    KNOWLEDGE_BOOK_CLICK("preventKnowledgebookClick", false),
    MOVED_WRONGLY_MESSAGES("messages" + ".movedWrongly", true),
    MOVED_QUICKLY_MESSAGES("messages" + ".movedQuickly", true),
    F3_NAME("f3name", "&b&lDeepslateMC"),
    TABLIST_ENABLED("tablist" + ".enabled", false),
    TABLIST_HEADER("tablist" + ".header", List.of("<dark_red>This is the", "<dark_red>default header")),
    TABLIST_FOOTER("tablist" + ".footer", List.of("<gold>This is the", "<gold>default footer")),
    PLAYERLIST_HOVER_ENABLED("playerlistHover" + ".enabled", false),
    PLAYERLIST_HOVER_LINES("playerlistHover" + ".lines", List.of("&6This is the", "&6default text")),
    BOSSBAR_ENABLED("bossbar" + ".enabled", false),
    BOSSBAR_COLOR("bossbar" + ".color", BossBar.Color.WHITE.name()),
    BOSSBAR_PROGRESS("bossbar" + ".progress", 1.0f),
    BOSSBAR_STYLE("bossbar" + ".style", BossBar.Overlay.PROGRESS.name()),
    BOSSBAR_TEXT("bossbar" + ".text", "<gold>Custom Bossbar"),
    ACTIONBAR_ENABLED("actionbar" + ".enabled", false),
    MINIMESSAGE_MOTD("miniMessageMotdSupport", false),
    ACTIONBAR_TEXT("actionbar" + ".text", "<gold>Custom Actionbar"),
    SPECTATOR_MODE_PACKETS("sendSpectatorModePackets", true),
    SERVER_LINKS("serverLinks", ""),
    ENABLE_TABLIST("enableTablist", true);

    final String key;
    final Object defaultValue;

    ConfigEntry(String key, Object defaultValue) {
        this.key = key;
        this.defaultValue = defaultValue;
    }
}
