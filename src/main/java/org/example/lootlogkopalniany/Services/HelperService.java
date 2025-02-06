package org.example.lootlogkopalniany.Services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HelperService {
    public static String extractRarity(String stat) {
        Pattern pattern = Pattern.compile("rarity=([^;]+)");
        Matcher matcher = pattern.matcher(stat);
        return matcher.find() ? matcher.group(1) : "brak";
    }
}
