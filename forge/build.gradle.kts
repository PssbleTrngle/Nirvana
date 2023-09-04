val mc_version: String by extra
val registrate_forge_version: String by extra
val jei_version: String by extra
val jeed_version: String by extra

forge {
    enableMixins()

    dependOn(project(":common"))
    includesMod("com.tterrag.registrate:Registrate:${registrate_forge_version}")
}

dependencies {
    modCompileOnly("mezz.jei:jei-${mc_version}-common-api:${jei_version}")
    modCompileOnly("mezz.jei:jei-${mc_version}-forge-api:${jei_version}")

    if (!env.isCI) {
        modRuntimeOnly("mezz.jei:jei-${mc_version}-forge:${jei_version}")
        modRuntimeOnly("maven.modrinth:just-enough-effect-descriptions-jeed:${jeed_version}")
    }
}

uploadToCurseforge()
uploadToModrinth()