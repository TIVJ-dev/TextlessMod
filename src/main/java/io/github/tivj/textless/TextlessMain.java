package io.github.tivj.textless;

import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = TextlessMain.MODID, name = TextlessMain.NAME, version = "1.0")
public class TextlessMain {
    public static final String MODID = "textless";
    public static final String NAME = "Textless";
    public static final Logger LOGGER = LogManager.getLogger("textless");
    @Mod.Instance(MODID)
    public static TextlessMain INSTANCE;

    public TextlessMain() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        ClientCommandHandler.instance.registerCommand(new ToggleCommand());
    }
}