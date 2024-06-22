package hoangdz.discord.dokiep.Commons;

import hoangdz.discord.dokiep.Dto.PlayerUpLevelMsgDto;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Commons {
    public static PlayerUpLevelMsgDto ParseRedisMessage(String msg) {
        final String regex = "\\[1\\]\\.(.*?)\\.(\\d+)-(\\d+)";

        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(msg);

        while (matcher.find()) {
            return new PlayerUpLevelMsgDto(
                    matcher.group(1),
                    Integer.parseInt(matcher.group(2)),
                    Integer.parseInt(matcher.group(3))
            );
        }
        return null;
    }
}


