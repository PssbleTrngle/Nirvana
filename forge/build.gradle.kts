import net.minecraftforge.gradle.common.util.MinecraftExtension
import org.spongepowered.asm.gradle.plugins.MixinExtension

val mc_version: String by extra
val mod_id: String by extra
val registrate_forge_version: String by extra
val jei_version: String by extra
val jeed_version: String by extra
val fd_forge_version: String by extra
val create_forge_version: String by extra
val flywheel_forge_version: String by extra
val freecam_forge_version: String by extra

forge {
    enableMixins()

    dependOn(project(":common"))
    includesMod("com.tterrag.registrate:Registrate:${registrate_forge_version}")
}

configure<MixinExtension> {
    config("$mod_id.forge.mixins.json")
}

configure<MinecraftExtension> {
    runs {
        forEach {
            // required for flywheel in dev
            it.property("production", "true")
        }
    }
}

dependencies {
    modCompileOnly("mezz.jei:jei-${mc_version}-common-api:${jei_version}")
    modCompileOnly("mezz.jei:jei-${mc_version}-forge-api:${jei_version}")
    modImplementation("com.simibubi.create:create-${mc_version}:${create_forge_version}:slim") {
        isTransitive = false
    }

    if (!env.isCI) {
        modRuntimeOnly("mezz.jei:jei-${mc_version}-forge:${jei_version}")
        modRuntimeOnly("maven.modrinth:just-enough-effect-descriptions-jeed:${jeed_version}")
        modRuntimeOnly("maven.modrinth:farmers-delight:${fd_forge_version}")
        modRuntimeOnly("maven.modrinth:freecam:${freecam_forge_version}")
        modRuntimeOnly("com.jozufozu.flywheel:flywheel-forge-${mc_version}:${flywheel_forge_version}")
    }
}

uploadToCurseforge()
uploadToModrinth()