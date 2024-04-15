package keepmydw;

import java.util.function.BiPredicate;
import java.util.function.Supplier;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pixelmonmod.pixelmon.Pixelmon;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerAboutToStartEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.FMLNetworkConstants;

@Mod(KeepMyDW.MODID)
public class KeepMyDW {
	private static String modName = "KeepMyDW";
	public static final String MODID = "keepmydw"; 
	
    public static final Logger LOGGER = LogManager.getLogger(modName);

    public KeepMyDW() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(this);
        ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.DISPLAYTEST, () -> Pair.<Supplier<String>, BiPredicate<String, Boolean>>of(() -> FMLNetworkConstants.IGNORESERVERONLY, (a, b) -> true));

    }

    private void setup(final FMLCommonSetupEvent event) {

    }
    
    @SubscribeEvent
    public void preInit(FMLServerAboutToStartEvent e) {
        Pixelmon.EVENT_BUS.register(new PixelmonEventHandler());
	}


}
