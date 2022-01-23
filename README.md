<img src=".github/assets/logo.png" width="200px" align="right">
<div align="center">

# DeepslateMC
### A private [Patina](https://github.com/PatinaMC/Patina) fork for my own Minecraft Server
<br/>
</div>

I will start using it once [Patina](https://github.com/PatinaMC/Patina) becomes officially stable

This fork serves two purposes:
- I learn stuff about [Git](https://git-scm.com/), [Paperweight](https://github.com/PaperMC/paperweight) and Patches
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

## Download
**Warning!**: Because the upstream is still quite experimental, this fork is as well

Download [here](https://pascalpex.ddns.net/files/deepslate/DeepslateMC.jar)
