package supercoder79.simplexterrain.mixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.world.level.LevelGeneratorType;
import supercoder79.simplexterrain.world.WorldType;

@Mixin(LevelGeneratorType.class)
public class MixinLevelGeneratorTypeClient {
	@Shadow @Final private String name;

	@Inject(at = @At("HEAD"), method = "getTypeFromName", cancellable = true)
	private static void getTypeFromName(String name, CallbackInfoReturnable<LevelGeneratorType> info) {
		if (WorldType.STR_TO_WT_MAP.containsKey(name)) {
			info.setReturnValue(WorldType.STR_TO_WT_MAP.get(name).generatorType);
		}
	}

	//dirty hack but i can't be assed to figure out why the lang file won't work
	@Inject(method = "getTranslationKey", at = @At("HEAD"), cancellable = true)
	@Environment(EnvType.CLIENT)
	void getTranslationKey(CallbackInfoReturnable<String> cir) {
		if (this.name.equals("simplex")) cir.setReturnValue("Simplex Terrain");
	}
}