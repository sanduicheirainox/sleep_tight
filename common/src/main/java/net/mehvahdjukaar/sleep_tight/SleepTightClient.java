package net.mehvahdjukaar.sleep_tight;

import net.mehvahdjukaar.moonlight.api.platform.ClientPlatformHelper;
import net.mehvahdjukaar.sleep_tight.client.HammockBlockTileRenderer;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;

import java.util.Arrays;
import java.util.Comparator;

public class SleepTightClient {

    public static final ModelLayerLocation NIGHT_BAG = loc("night_bag");
    public static final ModelLayerLocation HAMMOCK = loc("hammock");
    public static final ResourceLocation BED_SHEET = new ResourceLocation("textures/atlas/beds.png");

    public static final Material[] HAMMOCK_TEXTURES = Arrays.stream(DyeColor.values())
            .sorted(Comparator.comparingInt(DyeColor::getId))
            .map(dyeColor -> new Material(BED_SHEET, SleepTight.res("entity/hammocks/" + dyeColor.getName())))
            .toArray(Material[]::new);

    public static void init() {
        ClientPlatformHelper.addModelLayerRegistration(SleepTightClient::registerLayers);
        ClientPlatformHelper.addEntityRenderersRegistration(SleepTightClient::registerEntityRenderers);
        ClientPlatformHelper.addBlockEntityRenderersRegistration(SleepTightClient::registerBlockEntityRenderers);
        ClientPlatformHelper.addAtlasTextureCallback(BED_SHEET, SleepTightClient::addTextures);
    }


    public static void setup() {
    }

    private static ModelLayerLocation loc(String name) {
        return new ModelLayerLocation(SleepTight.res(name), name);
    }

    private static void addTextures(ClientPlatformHelper.AtlasTextureEvent event) {
        Arrays.stream(HAMMOCK_TEXTURES).forEach(e -> event.addSprite(e.texture()));
    }

    private static void registerLayers(ClientPlatformHelper.ModelLayerEvent event) {
        event.register(HAMMOCK, HammockBlockTileRenderer::createLayer);
    }

    private static void registerEntityRenderers(ClientPlatformHelper.EntityRendererEvent event) {
    }

    private static void registerBlockEntityRenderers(ClientPlatformHelper.BlockEntityRendererEvent event) {
        event.register(SleepTight.HAMMOCK_TILE.get(), HammockBlockTileRenderer::new);
    }

}