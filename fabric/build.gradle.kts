val mc_version: String by extra
val registrate_fabric_version: String by extra
val jei_version: String by extra

fabric {
    enableMixins()
    dataGen()

    dependOn(project(":common"))
    includesMod("com.tterrag.registrate_fabric:Registrate:${registrate_fabric_version}")
}

dependencies {
    if(!env.isCI) {
        modRuntimeOnly("mezz.jei:jei-${mc_version}-fabric:${jei_version}")
    }
}

uploadToCurseforge()
uploadToModrinth()