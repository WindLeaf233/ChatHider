package moe.windleaf.chathider.utils;

import net.minecraft.entity.Entity;
import net.minecraft.util.ChatComponentText;

import java.util.Map;

/**
 * moe.windleaf.chathider.utils.PlayerUtil
 *
 * @author WindLeaf
 * @create 2021/9/19 18:17
 */
public class PlayerUtil {

    public static void sendMessage(Entity entity, String message) {
        entity.addChatMessage(new ChatComponentText(StringUtil.formatColorCode(message)));
    }

    public static void sendHelpList(Entity entity, Map<String, String> helpMap) {
        for (String s : helpMap.keySet()) {
            PlayerUtil.sendMessage(entity, "&6" + s.replace("[", "&e[").replace("]", "]&r") + " &f» &a" + helpMap.get(s));
        }
    }

}
