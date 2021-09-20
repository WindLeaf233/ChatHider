package moe.windleaf.chathider;

import moe.windleaf.chathider.commands.ChatHiderCommand;
import moe.windleaf.chathider.utils.ConfigUtil;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

/**
 * moe.windleaf.chathider.ChatHider
 *
 * @author WindLeaf
 * @create 2021/8/12 17:25
 */
@Mod(
        modid = ChatHider.MODID,
        version = ChatHider.VERSION,
        name = "ChatHider"
)
public class ChatHider {

    @Mod.Instance
    public static ChatHider INSTANCE;

    public static final String MODID = "chathider";
    public static final String VERSION = "@VERSION@";

    public final Logger logger = LogManager.getLogger("ChatHider");
    public static Boolean ENABLED = true;
    public static File configFile = new File("config/ChatHider.cfg");
    public static ConfigUtil ConfigUtil = new ConfigUtil();

    @SuppressWarnings("all")
    private void initConfig() {
        try {
            if (!configFile.exists()) configFile.createNewFile();
            ConfigUtil.initConfiguration();
        } catch (IOException e) {
            logger.error("Cannot init configuration!");
        }
    }

    @Mod.EventHandler
    public void onFMLInitEvent(FMLInitializationEvent e) {
        logger.info("Loading ChatHider " + ChatHider.VERSION + "...");
        this.initConfig();
        MinecraftForge.EVENT_BUS.register(new ChatEventHandler());
        ClientCommandHandler.instance.registerCommand(new ChatHiderCommand());
    }

}
