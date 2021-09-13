# DeepslateMC - A private Sugarcane fork
DeepslateMC - A private Sugarcane fork for my own Minecraft Server
I will start using it once Sugarcane becomes officially stable

This fork serves two purposes:
- I learn stuff about Git, Paperclip and Patches
- Small features replace minor plugins on my server

I know that some patches are a mess

## Building
```
./gradlew applyPatches
./gradlew build
./gradlew paperclip
```

## Adding a Patch
```
./gradlew applyPatches
Edit the code
./gradlew build
./gradlew paperclip
Test the code
cd into submodule (api or server)
git add .
git commit -m PATCHNAME
cd into root dir
./gradlew rebuildPatches
Push/PR
```
