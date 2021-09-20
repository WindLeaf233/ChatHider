package moe.windleaf.chathider.commands;

import moe.windleaf.chathider.ChatHider;
import moe.windleaf.chathider.utils.PlayerUtil;
import moe.windleaf.chathider.utils.StringUtil;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;

import java.util.*;

/**
 * moe.windleaf.chathider.commands.ChatHiderCommand
 *
 * @author WindLeaf
 * @create 2021/8/12 18:13
 */
public class ChatHiderCommand extends CommandBase {

    @Override
    public String getCommandName() {
        return "chathider";
    }

    @Override
    public String getCommandUsage(ICommandSender iCommandSender) {
        return StringUtil.formatColorCode("&cPlease type &6/chathider help &cto see the usage!");
    }

    @Override
    public List<String> getCommandAliases() {
        return Collections.singletonList("ch");
    }

    @Override
    public void processCommand(ICommandSender iCommandSender, String[] strings) {
        Entity entity = (Entity) iCommandSender;
        String WRONG_ARGUMENTS = "&cWrong arguments! Please type &6/chathider help &cto see the usage!";
        String MULTI_ARGS = "&eIs this a whole sentence or phrase?\n&eIf it is, please append a character &b` &eto the first word's beginning; If not, please remove the others arguments!";

        if (strings.length > 0) {
            if ("help".equals(strings[0])) {
                PlayerUtil.sendMessage(entity, "&2ChatHider v" + ChatHider.VERSION + " &7mod by &3WindLeaf_qwq");
                PlayerUtil.sendMessage(entity, "&7Alias command: /ch");
                @SuppressWarnings("all") Map<String, String> helpMap = new HashMap<String, String>();
                helpMap.put("/ch help", "Show this help page");
                helpMap.put("/ch enable", "Temporary enable mod");
                helpMap.put("/ch disable", "Temporary disable mod");
                helpMap.put("/ch add [keyword]", "Add a keyword to blacklist");
                helpMap.put("/ch remove [keyword]", "Remove a keyword from blacklist");
                helpMap.put("/ch list", "Show all blocked keywords");
                helpMap.put("/ch clear", "Clear all keywords");
                helpMap.put("/ch convert [string]/&e`clear&r", "Convert blocked keywords to string or clear string, clear string means it won't show message that contains keyword");
                PlayerUtil.sendHelpList(entity, helpMap);
            } else if ("enable".equals(strings[0])) {
                ChatHider.ENABLED = true;
                PlayerUtil.sendMessage(entity, "&aChatHider mod enabled!");
            } else if ("disable".equals(strings[0])) {
                ChatHider.ENABLED = false;
                PlayerUtil.sendMessage(entity, "&cChatHider mod disabled!");
            } else if ("add".equals(strings[0])) {
                if (strings.length >= 2) {
                    @SuppressWarnings("all") ArrayList<String> tempList = new ArrayList<String>(Arrays.asList(strings));
                    tempList.remove("add");
                    StringBuilder sb = new StringBuilder();
                    for (String s : tempList) {
                        sb.append(s).append(" ");
                    }
                    String keyword = sb.toString().startsWith("`") ? sb.toString().trim().replace("`", "") : sb.toString().trim();
                    if (!strings[1].contains("`") && strings.length > 2) {
                        PlayerUtil.sendMessage(entity, MULTI_ARGS);
                    } else {
                        if (!ChatHider.ConfigUtil.keywordsList.contains(keyword)) {
                            ChatHider.ConfigUtil.addKeyword(keyword);
                            PlayerUtil.sendMessage(entity, "&aSuccessfully added keyword &2[" + keyword + "] &ato the blacklist!");
                        } else {
                            PlayerUtil.sendMessage(entity, "&cThere's already a keyword &2[" + keyword + "] &cin blacklist!");
                        }
                    }
                } else {
                    PlayerUtil.sendMessage(entity, WRONG_ARGUMENTS);
                }
            } else if ("remove".equals(strings[0])) {
                if (strings.length >= 2) {
                    @SuppressWarnings("all") ArrayList<String> tempList = new ArrayList<String>(Arrays.asList(strings));
                    tempList.remove("remove");
                    StringBuilder sb = new StringBuilder();
                    for (String s : tempList) {
                        sb.append(s).append(" ");
                    }
                    String keyword = sb.toString().startsWith("`") ? sb.toString().trim().replace("`", "") : sb.toString().trim();
                    if (!strings[1].contains("`") && strings.length > 2) {
                        PlayerUtil.sendMessage(entity, MULTI_ARGS);
                    } else {
                        if (ChatHider.ConfigUtil.keywordsList.contains(keyword)) {
                            ChatHider.ConfigUtil.removeKeyword(keyword);
                            PlayerUtil.sendMessage(entity, "&aSuccessfully removed keyword &2[" + keyword + "] &afrom the blacklist!");
                        } else {
                            PlayerUtil.sendMessage(entity, "&cThere's no keyword equals &2[" + keyword + "] &cin blacklist!");
                        }
                    }
                } else {
                    PlayerUtil.sendMessage(entity, WRONG_ARGUMENTS);
                }
            } else if ("list".equals(strings[0])) {
                if (ChatHider.ConfigUtil.keywordsList.size() != 0) {
                    for (String s : ChatHider.ConfigUtil.keywordsList) {
                        PlayerUtil.sendMessage(entity, "&f - &2" + s);
                    }
                } else {
                    PlayerUtil.sendMessage(entity, "&eThere's no keywords in blacklist!");
                }
            } else if ("clear".equals(strings[0])) {
                ChatHider.ConfigUtil.clearKeywords();
                PlayerUtil.sendMessage(entity, "&aSuccessfully cleared the blacklist!");
            } else if ("convert".equals(strings[0])) {
                if (strings.length >= 2) {
                    @SuppressWarnings("all") ArrayList<String> tempList = new ArrayList<String>(Arrays.asList(strings));
                    tempList.remove("convert");
                    StringBuilder sb = new StringBuilder();
                    for (String s : tempList) {
                        sb.append(s).append(" ");
                    }
                    String arg = sb.toString().startsWith("`") ? sb.toString().trim().replace("`", "") : sb.toString().trim();
                    if (!strings[1].contains("`") && strings.length > 2) {
                        PlayerUtil.sendMessage(entity, MULTI_ARGS);
                    } else {
                        if (!arg.equals("`clear")) {
                            ChatHider.ConfigUtil.setConvertString(arg);
                            PlayerUtil.sendMessage(entity, "&aNow blocked keywords will be converted to &2" + arg + "&a!");
                        } else {
                            ChatHider.ConfigUtil.setConvertString(null);
                            PlayerUtil.sendMessage(entity, "&aNow message that contains keywords won't show to you!");
                        }
                    }
                } else {
                    PlayerUtil.sendMessage(entity, WRONG_ARGUMENTS);
                }
            } else {
                PlayerUtil.sendMessage(entity, WRONG_ARGUMENTS);
            }
        } else {
            PlayerUtil.sendMessage(entity, "&cPlease type &6/chathider help &cto see the usage!");
        }
        System.out.println("Get arguments " + Arrays.toString(strings));
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender iCommandSender) {
        return true;
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender iCommandSender, String[] strings, BlockPos blockPos) {
        return null;
    }

    @Override
    public boolean isUsernameIndex(String[] strings, int i) {
        return false;
    }

    @Override
    public int compareTo(ICommand o) {
        return 1;
    }

}
