package moe.windleaf.chathider.utils;

import moe.windleaf.chathider.ChatHider;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * moe.windleaf.chathider.utils.ConfigUtil
 *
 * @author WindLeaf
 * @create 2021/9/19 20:40
 */
public class ConfigUtil {

    public Configuration configuration;
    public Property keywordsProperty;
    @SuppressWarnings("all") public ArrayList<String> keywordsList = new ArrayList<String>();
    public Property convertStringProperty;
    public String convertString = "***";

    public void initConfiguration() {
        this.configuration = new Configuration(ChatHider.configFile);
        this.configuration.load();

        this.keywordsProperty = this.configuration.get(Configuration.CATEGORY_GENERAL,
                "keywords",
                new String[]{},
                "Blocked keywords list (type: StringList)");
        this.keywordsList.addAll(Arrays.asList(this.keywordsProperty.getStringList()));
        this.keywordsList.remove("");

        this.convertStringProperty = this.configuration.get(Configuration.CATEGORY_GENERAL,
                "convertString",
                "***",
                "Convert blocked keywords to string (type: String)");
        this.convertString = this.convertStringProperty.getString().equals("```NULL```")
                ? null
                : this.convertStringProperty.getString();
    }

    public void addKeyword(String keyword) {
        this.keywordsList.add(keyword);
        @SuppressWarnings("all") ArrayList<String> tempList = new ArrayList<String>(Arrays.asList(this.keywordsProperty.getStringList()));
        tempList.add(keyword);
        this.keywordsProperty.set(tempList.toArray(new String[0]));
        this.configuration.save();
    }

    public void removeKeyword(String keyword) {
        this.keywordsList.remove(keyword);
        @SuppressWarnings("all") ArrayList<String> tempList = new ArrayList<String>(Arrays.asList(this.keywordsProperty.getStringList()));
        tempList.remove(keyword);
        this.keywordsProperty.set(tempList.toArray(new String[0]));
        this.configuration.save();
    }

    public void clearKeywords() {
        this.keywordsList.clear();
        this.keywordsProperty.set(new String[]{});
        this.configuration.save();
    }

    public void setConvertString(String string) {
        String s = string == null ? "```NULL```" : string;
        this.convertString = string;
        this.convertStringProperty.set(s);
        this.configuration.save();
    }

}
