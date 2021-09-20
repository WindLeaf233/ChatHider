package moe.windleaf.chathider;

import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * moe.windleaf.chathider.ChatEventHandler
 *
 * @author WindLeaf
 * @create 2021/9/19 2:08
 */
public final class ChatEventHandler {

    @SubscribeEvent
    public void onClientChatReceived(ClientChatReceivedEvent e) {
        if (ChatHider.ENABLED) {
            String message = e.message.getUnformattedText();
            for (String s : ChatHider.ConfigUtil.keywordsList) {
                if (message.contains(s)) {
                    if (ChatHider.ConfigUtil.convertString == null) {
                        e.setCanceled(true);
                    } else {
                        e.message = new ChatComponentText(e.message.getFormattedText().replace(s, ChatHider.ConfigUtil.convertString));
                    }
                }
            }
        }
    }

}
