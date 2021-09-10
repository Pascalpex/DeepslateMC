# DeepslateMC - A private Sugarcane fork
DeepslateMC - A private Sugarcane fork

## Building
```
./gradlew applyPatches
./gradlew build
./gradlew paperclip
```

## Tasks

```
Paperweight tasks
-----------------
applyApiPatches
applyPatches
applyServerPatches
cleanCache - Delete the project setup cache and task outputs.
generateDevelopmentBundle
paperclipJar - Build a runnable paperclip jar
rebuildApiPatches
rebuildPatches
rebuildServerPatches
reobfJar - Re-obfuscate the built jar to obf mappings
runDev - Spin up a non-shaded non-remapped test server
runReobf - Spin up a test server from the reobfJar output jar
runShadow - Spin up a test server from the shadowJar archiveFile
```
